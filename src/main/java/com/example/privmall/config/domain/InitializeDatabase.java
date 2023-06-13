package com.example.privmall.config.domain;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Category;
import com.example.privmall.repository.ArticleRepository;
import com.example.privmall.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Profile(value = "local") @Component
public class InitializeDatabase {

    private final InitializeService initializeService;

    public InitializeDatabase(InitializeService initializeService) {
        System.out.println("InitializeDatabase.InitializeDatabase(3)");
        this.initializeService = initializeService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitializeDatabase.postConstruct(4)");
        initializeService.initialize();
    }

    @Component
    static class InitializeService {

        private final UserAccountRepository userAccountRepository;

        private final ArticleRepository articleRepository;

        private final PasswordEncoder passwordEncoder;

        public InitializeService(UserAccountRepository userAccountRepository,
                                 ArticleRepository articleRepository,
                                 PasswordEncoder passwordEncoder) {
            this.userAccountRepository = userAccountRepository;
            this.articleRepository = articleRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("InitializeService.postConstruct(2)");
        }

        @Transactional
        public void initialize() {
            System.out.println("InitializeService.initialize(5)");
            IntStream.rangeClosed(1,13).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("test" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("test" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.CS)
                        .title("Title" + value)
                        .content("content" + value)
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1, 2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("dummy" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("anotherone" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Spring DummyTitle" + value)
                        .content("""
                                ## Development Status
                                                                
                                frp is currently under development. You can try the latest release version in the `master` branch, or use the `dev` branch to access the version currently in development.
                                                                
                                We are currently working on version 2 and attempting to perform some code refactoring and improvements. However, please note that it will not be compatible with version 1.
                                                                
                                We will transition from version 0 to version 1 at the appropriate time and will only accept bug fixes and improvements, rather than big feature requests.
                                                                
                                ## Example Usage
                                                                
                                To begin, download the latest program for your operating system and architecture from the [Release](https://github.com/fatedier/frp/releases) page.
                                                                
                                Next, place the `frps` binary and `frps.ini` configuration file on Server A, which has a public IP address.
                                                                
                                Finally, place the `frpc` binary and `frpc.ini` configuration file on Server B, which is located on a LAN that cannot be directly accessed from the public internet.
                                                                
                                ### Access your computer in a LAN network via SSH
                                                                
                                1. Modify `frps.ini` on server A by setting the `bind_port` for frp clients to connect to:
                                                                
                                  ```ini
                                  # frps.ini
                                  [common]
                                  bind_port = 7000
                                  ```
                                                                
                                2. Start `frps` on server A:
                                                                
                                  `./frps -c ./frps.ini`
                                                                
                                3. Modify `frpc.ini` on server B and set the `server_addr` field to the public IP address of your frps server:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [ssh]
                                  type = tcp
                                  local_ip = 127.0.0.1
                                  local_port = 22
                                  remote_port = 6000
                                  ```
                                                                
                                Note that the `local_port` (listened on the client) and `remote_port` (exposed on the server) are used for traffic going in and out of the frp system, while the `server_port` is used for communication between frps and frpc.
                                                                
                                4. Start `frpc` on server B:
                                                                
                                  `./frpc -c ./frpc.ini`
                                                                
                                5. To access server B from another machine through server A via SSH (assuming the username is `test`), use the following command:
                                                                
                                  `ssh -oPort=6000 test@x.x.x.x`
                                                                
                                ### Accessing Internal Web Services with Custom Domains in LAN
                                                                
                                Sometimes we need to expose a local web service behind a NAT network to others for testing purposes with our own domain name.
                                                                
                                Unfortunately, we cannot resolve a domain name to a local IP. However, we can use frp to expose an HTTP(S) service.
                                                                
                                1. Modify `frps.ini` and set the HTTP port for vhost to 8080:
                                                                
                                  ```ini
                                  # frps.ini
                                  [common]
                                  bind_port = 7000
                                  vhost_http_port = 8080
                                  ```
                                                                
                                2. Start `frps`:
                                                                
                                  `./frps -c ./frps.ini`
                                                                
                                3. Modify `frpc.ini` and set `server_addr` to the IP address of the remote frps server. Specify the `local_port` of your web service:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [web]
                                  type = http
                                  local_port = 80
                                  custom_domains = www.example.com
                                  ```
                                                                
                                4. Start `frpc`:
                                                                
                                  `./frpc -c ./frpc.ini`
                                                                
                                5. Map the A record of `www.example.com` to either the public IP of the remote frps server or a CNAME record pointing to your original domain.
                                                                
                                6. Visit your local web service using url `http://www.example.com:8080`.
                                                                
                                ### Forward DNS query requests
                                                                
                                1. Modify `frps.ini`:
                                                                
                                  ```ini
                                  # frps.ini
                                  [common]
                                  bind_port = 7000
                                  ```
                                                                
                                2. Start `frps`:
                                                                
                                  `./frps -c ./frps.ini`
                                                                
                                3. Modify `frpc.ini` and set `server_addr` to the IP address of the remote frps server. Forward DNS query requests to the Google Public DNS server `8.8.8.8:53`:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [dns]
                                  type = udp
                                  local_ip = 8.8.8.8
                                  local_port = 53
                                  remote_port = 6000
                                  ```
                                                                
                                4. Start frpc:
                                                                
                                  `./frpc -c ./frpc.ini`
                                                                
                                5. Test DNS resolution using the `dig` command:
                                                                
                                  `dig @x.x.x.x -p 6000 www.google.com`
                                                                
                                ### Forward Unix Domain Socket
                                                                
                                Expose a Unix domain socket (e.g. the Docker daemon socket) as TCP.
                                                                
                                Configure `frps` as above.
                                                                
                                1. Start `frpc` with the following configuration:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [unix_domain_socket]
                                  type = tcp
                                  remote_port = 6000
                                  plugin = unix_domain_socket
                                  plugin_unix_path = /var/run/docker.sock
                                  ```
                                                                
                                2. Test the configuration by getting the docker version using `curl`:
                                                                
                                  `curl http://x.x.x.x:6000/version`
                                                                
                                ### Expose a simple HTTP file server
                                                                
                                Expose a simple HTTP file server to access files stored in the LAN from the public Internet.
                                                                
                                Configure `frps` as described above, then:
                                                                
                                1. Start `frpc` with the following configuration:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [test_static_file]
                                  type = tcp
                                  remote_port = 6000
                                  plugin = static_file
                                  plugin_local_path = /tmp/files
                                  plugin_strip_prefix = static
                                  plugin_http_user = abc
                                  plugin_http_passwd = abc
                                  ```
                                                                
                                2. Visit `http://x.x.x.x:6000/static/` from your browser and specify correct username and password to view files in `/tmp/files` on the `frpc` machine.
                                                                
                                ### Enable HTTPS for a local HTTP(S) service
                                                                
                                You may substitute `https2https` for the plugin, and point the `plugin_local_addr` to a HTTPS endpoint.
                                                                
                                1. Start `frpc` with the following configuration:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [test_https2http]
                                  type = https
                                  custom_domains = test.example.com
                                                                
                                  plugin = https2http
                                  plugin_local_addr = 127.0.0.1:80
                                  plugin_crt_path = ./server.crt
                                  plugin_key_path = ./server.key
                                  plugin_host_header_rewrite = 127.0.0.1
                                  plugin_header_X-From-Where = frp
                                  ```
                                                                
                                2. Visit `https://test.example.com`.
                                                                
                                ### Expose your service privately
                                                                
                                To mitigate risks associated with exposing certain services directly to the public network, STCP (Secret TCP) mode requires a preshared key to be used for access to the service from other clients.
                                                                
                                Configure `frps` same as above.
                                                                
                                1. Start `frpc` on machine B with the following config. This example is for exposing the SSH service (port 22), and note the `sk` field for the preshared key, and that the `remote_port` field is removed here:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [secret_ssh]
                                  type = stcp
                                  sk = abcdefg
                                  local_ip = 127.0.0.1
                                  local_port = 22
                                  ```
                                                                
                                2. Start another `frpc` (typically on another machine C) with the following config to access the SSH service with a security key (`sk` field):
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                                                
                                  [secret_ssh_visitor]
                                  type = stcp
                                  role = visitor
                                  server_name = secret_ssh
                                  sk = abcdefg
                                  bind_addr = 127.0.0.1
                                  bind_port = 6000
                                  ```
                                                                
                                3. On machine C, connect to SSH on machine B, using this command:
                                                                
                                  `ssh -oPort=6000 127.0.0.1`
                                                                
                                ### P2P Mode
                                                                
                                **xtcp** is designed to transmit large amounts of data directly between clients. A frps server is still needed, as P2P here only refers to the actual data transmission.
                                                                
                                Note that it may not work with all types of NAT devices. You might want to fallback to stcp if xtcp doesn't work.
                                                                
                                1. Start `frpc` on machine B, and expose the SSH port. Note that the `remote_port` field is removed:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                  # set up a new stun server if the default one is not available.
                                  # nat_hole_stun_server = xxx
                                                                
                                  [p2p_ssh]
                                  type = xtcp
                                  sk = abcdefg
                                  local_ip = 127.0.0.1
                                  local_port = 22
                                  ```
                                                                
                                2. Start another `frpc` (typically on another machine C) with the configuration to connect to SSH using P2P mode:
                                                                
                                  ```ini
                                  # frpc.ini
                                  [common]
                                  server_addr = x.x.x.x
                                  server_port = 7000
                                  # set up a new stun server if the default one is not available.
                                  # nat_hole_stun_server = xxx
                                                                
                                  [p2p_ssh_visitor]
                                  type = xtcp
                                  role = visitor
                                  server_name = p2p_ssh
                                  sk = abcdefg
                                  bind_addr = 127.0.0.1
                                  bind_port = 6000
                                  # when automatic tunnel persistence is required, set it to true
                                  keep_tunnel_open = false
                                  ```
                                                                
                                3. On machine C, connect to SSH on machine B, using this command:
                                                                
                                  `ssh -oPort=6000 127.0.0.1`
                                                                
                                ## Features
                                                                
                                ### Configuration Files
                                                                
                                Read the full example configuration files to find out even more features not described here.
                                                                
                                """)
                        .hashtag("Spring")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("roorun" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("anotherone" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Vue DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("Vue")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("rookan" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another2" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Python DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("Python")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("toberoot" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another3" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Bash DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("Bash")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("tobeadmin" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another4" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Java DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("Java")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("root" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another5" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("JavaScript DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("JavaScript")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("Administrator" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another5" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.DEV)
                        .title("Quasar DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("Quasar")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("backdoor" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another6" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.WRITEUP)
                        .title("HackTheBox DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("HackTheBox")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });

            IntStream.rangeClosed(1,2).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("dummy_shell" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("another7" + value)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .category(Category.WRITEUP)
                        .title("TryHackMe DummyTitle" + value)
                        .content("content" + value)
                        .hashtag("TryHackMe")
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });
        }
    }
}
