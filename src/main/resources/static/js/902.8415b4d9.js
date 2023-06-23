"use strict";(globalThis["webpackChunkr3vi1e37"]=globalThis["webpackChunkr3vi1e37"]||[]).push([[902],{7837:(e,a,t)=>{t.d(a,{cU:()=>i,qb:()=>o,yP:()=>c,z7:()=>d});var s=t(7524),l=t(501),r=t(1809);const{accessToken:n}=(0,r.Jk)((0,l.t)());function o(e){return s.Z.post("/api/posts",{...e},{headers:{Authorization:`Bearer ${n.value}`}})}function i(e){return s.Z.get("/api/posts",{params:e})}function c(e){return s.Z.get("/api/posts",{params:e})}function d(e){return s.Z.get(`/api/post/${e}`)}},9367:(e,a,t)=>{t.d(a,{Z:()=>Z});t(9665);var s=t(9835),l=t(6970),r=t(1957),n=t(8339),o=t(499);const i=e=>((0,s.dD)("data-v-4419f948"),e=e(),(0,s.Cn)(),e),c={class:"row q-gutter-lg"},d={class:"flex flex-center"},p={class:"text-center"},u={class:"col"},h={class:"title"},m={class:"row q-gutter-sm"},g={class:"col-3"},v={class:"row no-wrap items-center"},b={class:"text-overline text-red"},f=i((()=>(0,s._)("span",{class:"text-bold"}," · ",-1))),_={class:"text-caption"},x={key:0,class:"q-ml-lg"},w={__name:"PostComponent",props:{id:{type:Number,required:!1},title:{type:String,required:!0},content:{type:String,required:!0},hashtag:{type:Array[String],required:!0},createdAt:{type:String,required:!0},createdBy:{type:String,required:!0},category:{type:String,required:!0},shadow:{type:Boolean,required:!1,default:!0},border:{type:Boolean,required:!1,default:!0}},setup(e){const a=e,t=(0,o.iH)(!1),i=(0,o.iH)(!1),w=()=>{i.value=!i.value,t.value=!t.value},y=(0,n.tv)(),k=()=>y.push({name:"PostDetails",params:{id:a.id}}),q=new Date(a.createdAt),H=q.getFullYear(),S=String(q.getMonth()+1).padStart(2,"0"),C=String(q.getDate()).padStart(2,"0"),B=String(q.getHours()).padStart(2,"0"),Z=String(q.getMinutes()).padStart(2,"0"),z=`${H}-${S}-${C} ${B}:${Z}`,T=(0,s.Fl)({get:()=>a.content.slice(0,80)}),W=(0,s.Fl)({get:()=>{const e=Math.floor(a.content.split("\n").length/40);return 0===e?"1 min to read":`${e} min to read`}}),D=e=>"Spring"===e?"Spring":"Vue"===e?"Vue":"Python"===e?"Python":"Bash"===e?"Bash":"Java"===e?"Java":"JavaScript"===e?"JavaScript":"Quasar"===e?"Quasar":"HackTheBox"===e?"HackTheBox":"TryHackMe"===e?"TryHackMe":"Unknown",A=(0,s.Fl)((()=>{const e={shadow:"none",link:"Unknown",color:"Unknown",label:"Unknown"};return"Spring"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #6cb52d, 0 0 11px #6cb52d, 0 0 25px #6cb52d, 0 0 45px #6cb52d":"none",e.link="Spring.svg",e.color="Spring",e.label="Spring"):"Vue"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #42b883, 0 0 11px #42b883, 0 0 25px #42b883, 0 0 45px #42b883":"none",e.link="Vue.svg",e.color="Vue",e.label="Vue"):"Python"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #3d7daf, 0 0 11px #3d7daf, 0 0 25px #3d7daf, 0 0 45px #3d7daf":"none",e.link="Python.svg",e.color="Python",e.label="Python"):"Bash"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #fefefe, 0 0 11px #fefefe, 0 0 25px #fefefe, 0 0 45px #fefefe":"none",e.link="Bash.svg",e.color="Bash",e.label="Bash"):"Java"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #b07219, 0 0 11px #b07219, 0 0 25px #b07219, 0 0 45px #b07219":"none",e.link="Java.svg",e.color="Java",e.label="Java"):"JavaScript"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #f1e05a, 0 0 11px #f1e05a, 0 0 25px #f1e05a, 0 0 45px #f1e05a":"none",e.link="JavaScript.svg",e.color="JavaScript",e.label="JavaScript"):"Quasar"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #00b4ff, 0 0 11px #00b4ff, 0 0 25px #00b4ff, 0 0 45px #00b4ff":"none",e.link="Quasar.svg",e.color="Quasar",e.label="Quasar"):"HackTheBox"===a.hashtag[0]?(e.shadow=i.value?"0 0 3px #9fef00, 0 0 11px #9fef00, 0 0 25px #9fef00, 0 0 45px #9fef00":"none",e.link="HackTheBox.svg",e.color="HackTheBox",e.label="HackTheBox"):"TryHackMe"===a.hashtag[0]&&(e.shadow=i.value?"0 0 3px #ff0000, 0 0 11px #ff0000, 0 0 25px #ff0000, 0 0 45px #ff0000":"none",e.link="TryHackMe.svg",e.color="TryHackMe",e.label="TryHackMe"),e}));return(n,o)=>{const y=(0,s.up)("q-img"),q=(0,s.up)("q-avatar"),H=(0,s.up)("q-badge");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("article",{class:(0,l.C_)({hovered:i.value}),style:(0,l.j5)({boxShadow:e.shadow?A.value.shadow:"none",minHeight:"100px",padding:"15px",border:e.border?"1px solid":"none"}),onClick:o[0]||(o[0]=e=>k()),onMouseenter:o[1]||(o[1]=e=>w()),onMouseleave:o[2]||(o[2]=e=>w())},[(0,s.WI)(n.$slots,"default",{svgLink:A.value.link,timeToRead:W.value,content:T.value},(()=>[(0,s._)("div",c,[(0,s._)("div",null,[(0,s._)("div",d,[(0,s.Wm)(q,{size:"64px"},{default:(0,s.w5)((()=>[(0,s.Wm)(y,{width:"48px",src:A.value.link},null,8,["src"])])),_:1})]),(0,s._)("span",p,[(0,s.Wm)(H,{rounded:"",outline:"",color:A.value.color,label:A.value.label},null,8,["color","label"])])]),(0,s._)("div",u,[(0,s._)("div",{class:"row items-center no-wrap",style:(0,l.j5)({height:a.hashtag.slice(1).length>0?"64px":"100%"})},[(0,s._)("span",h,(0,l.zw)(e.title),1)],4),(0,s._)("div",m,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(a.hashtag.slice(1),(e=>((0,s.wg)(),(0,s.iD)("span",{key:e},[(0,s.Wm)(H,{rounded:"",outline:"",color:D(e),label:e},null,8,["color","label"])])))),128))])]),(0,s._)("div",g,[(0,s._)("div",v,[(0,s._)("span",b,(0,l.zw)(e.category.toUpperCase()),1),f,(0,s._)("span",_,(0,l.zw)(W.value),1)]),(0,s._)("div",null,[(0,s._)("span",null,"Created : "+(0,l.zw)(z)),(0,s._)("span",null,"Written By : "+(0,l.zw)(e.createdBy),1)])])])]))],38),(0,s.Wm)(r.uT,{name:"articleSlider"},{default:(0,s.w5)((()=>[t.value?((0,s.wg)(),(0,s.iD)("div",x,[(0,s.WI)(n.$slots,"slide")])):(0,s.kq)("",!0)])),_:3})],64)}}};var y=t(1639),k=t(1357),q=t(335),H=t(990),S=t(9984),C=t.n(S);const B=(0,y.Z)(w,[["__scopeId","data-v-4419f948"]]),Z=B;C()(w,"components",{QAvatar:k.Z,QImg:q.Z,QBadge:H.Z})},902:(e,a,t)=>{t.r(a),t.d(a,{default:()=>Se});t(9665);var s=t(9835),l=t(6970),r=t(7837),n=t(9367),o=t(45);const i={style:{width:"320px",height:"320px"}},c=(0,s._)("canvas",{id:"myChart"},null,-1),d=[c],p={__name:"ChartComponent",props:{chartType:{type:String},chartData:{type:Object},chartOptions:{type:Object}},setup(e){const a=e;o.ZP.defaults.color="#BADA55",o.ZP.defaults.borderColor="#adbac7";const t=(e,a,t)=>{const s=document.getElementById("myChart");new o.ZP(s,{type:e,data:a,options:t})};return(0,s.bv)((()=>{t(a.chartType,a.chartData,a.chartOptions)})),(e,a)=>((0,s.wg)(),(0,s.iD)("div",i,d))}},u=p,h=u,m=e=>((0,s.dD)("data-v-868cc864"),e=e(),(0,s.Cn)(),e),g=m((()=>(0,s._)("div",{style:{width:"auto","border-radius":"4px","margin-right":"0px"}},[(0,s._)("div",{class:"thm_avatar",style:{"background-image":"url('https://secure.gravatar.com/avatar/890fccd6ac01e013029ae2889d85fa96.jpg?s=200&d=robohash&r=x')"}})],-1))),v={style:{"margin-left":"25px",width:"235px",height:"56px","background-color":"#343c42","border-radius":"5px","text-align":"left","background-image":"url('https://assets.tryhackme.com/img/thm_logo_only.svg')","background-size":"23px 23px","background-position":"right 5px bottom 5px","background-repeat":"no-repeat"}},b={class:"thm_font",style:{"box-sizing":"content-box",height:"50px","white-space":"nowrap",overflow:"hidden","text-overflow":"ellipsis","padding-left":"8px","padding-top":"5px"}},f=m((()=>(0,s._)("p",{class:"thm_line",style:{"margin-bottom":"3px"}},[(0,s._)("span",{class:"thm_nickname"},"revi1337"),(0,s.Uk)(),(0,s._)("span",{class:"thm_rank"},"[0xA][Wizard]"),(0,s._)("br")],-1))),_={class:"thm_line",style:{"margin-bottom":"1px",display:"flex","align-items":"center"}},x=m((()=>(0,s._)("img",{class:"thm_icon thm_mr",src:"https://assets.tryhackme.com/img/badges/trophy.png",alt:"trophy"},null,-1))),w={class:"thm_stat thm_mr"},y=m((()=>(0,s._)("img",{class:"thm_icon thm_mr",src:"https://assets.tryhackme.com/img/badges/door.png",alt:"door"},null,-1))),k=m((()=>(0,s._)("span",{class:"thm_stat thm_mr"},"110",-1))),q=m((()=>(0,s._)("img",{class:"thm_icon thm_mr",src:"https://assets.tryhackme.com/img/badges/target.png",alt:"target"},null,-1))),H=m((()=>(0,s._)("span",{class:"thm_stat"},"16",-1))),S=m((()=>(0,s._)("br",null,null,-1))),C=m((()=>(0,s._)("p",{class:"thm_line"},[(0,s._)("a",{href:"https://www.tryhackme.com",target:"_blank",class:"thm_link"},"tryhackme.com")],-1))),B={__name:"TryHackMeBadgeComponent",props:{userRank:{type:Number,required:!0,default:18685}},setup(e){const a=()=>window.open("https://tryhackme.com/p/revi1337","_blank");return(t,r)=>((0,s.wg)(),(0,s.iD)("div",null,[(0,s._)("div",{id:"thm_badge",class:"thm_margin",onClick:a},[g,(0,s._)("div",v,[(0,s._)("div",b,[f,(0,s._)("p",_,[x,(0,s._)("span",w,(0,l.zw)(e.userRank),1),y,k,q,H,S]),C])])])]))}};var Z=t(1639);const z=(0,Z.Z)(B,[["__scopeId","data-v-868cc864"]]),T=z;var W=t(499),D=t(7524);function A(e="R3VI1E37"){return D.Z.get(`https://api.github.com/repos/Revi1337/${e}/commits`,{headers:{Authorization:"This is Secret"}})}function Q(){return D.Z.get("/api/data/tryhackme/users")}function j(e="revi1337"){return D.Z.get(`/api/data/tryhackme/user/${e}`)}function I(){return D.Z.get("/api/data/tryhackme/user/skills")}var U=t(3255);const M=e=>((0,s.dD)("data-v-4c5fd1dc"),e=e(),(0,s.Cn)(),e),P=M((()=>(0,s._)("p",{class:"text-h5 text-weight-bold"},"Recent Post",-1))),R={class:"row items-center justify-center"},J={class:"col-3 row items-center justify-center"},V={class:"col-7 row items-center justify-center q-ml-xl"},$={class:"q-gutter-y-md"},E={class:"q-ml-sm"},L={class:"text-h4 text-bold"},O={class:"text-caption"},Y={class:"text-overline text-red"},F=M((()=>(0,s._)("span",{class:"text-bold"}," · ",-1))),G={class:"text-caption"},N={class:"row items-center justify-center",style:{height:"400px"}},K=M((()=>(0,s._)("div",{class:"col-12 row items-center justify-center no-wrap q-px-lg"},[(0,s._)("p",{class:"col-6 text-h5 text-weight-bold q-mr-md"},"DEVLOG"),(0,s._)("p",{class:"col-6 text-h5 text-weight-bold"},"TRYHACKME")],-1))),X={class:"col-12 row items-center justify-center no-wrap q-pa-lg"},ee={class:"col-6 q-mr-md"},ae={class:"col-6"},te={class:"row no-wrap"},se={class:"col-auto",style:{width:"320px",height:"320px"}},le={class:"column justify-center items-center"},re={class:"q-mb-md"},ne={class:"row items-end justify-center no-wrap",style:{width:"210px",height:"70px"}},oe={class:"q-mr-md"},ie=M((()=>(0,s._)("span",null," ",-1))),ce={class:"text-center text-h6 text-weight-bold"},de={class:"text-center text-caption"},pe={key:0},ue={class:"text-caption text-light-green-12"},he={class:"text-center text-h6 text-weight-bold"},me={class:"text-center text-caption"},ge={__name:"IndexPage",setup(e){const a=(0,W.iH)([]),t=(0,W.iH)({page:1,size:4,category:"writeup"}),o=async e=>{try{const{data:t}=await(0,r.cU)(e);a.value=t}catch(t){console.error(t)}},i=(0,W.iH)(1),c=(0,W.iH)(3e3),d=(0,W.iH)([]),p=(0,W.iH)([]),u=async e=>{({data:d.value}=await A(e)),d.value.forEach((({commit:e})=>p.value.push({message:e.message.slice(0,30),committer:e.committer.name,email:e.committer.email,date:e.committer.date})))},m=(0,W.iH)({}),g=async()=>{({data:m.value}=await Q())},v=(0,W.iH)({}),b=async()=>{({data:v.value}=await j())},f=(0,s.Fl)((()=>Math.ceil(v.value.userRank/m.value.totalUsers*100))),_=e=>{const a=[];return e.forEach((e=>e>=100?a.push(100):a.push(e))),a},x=(0,W.iH)({fundamentals:"",linux:"",network:"",privesc:"",web:"",windows:""}),w=async()=>{let e=null;try{y.value=!1;const{data:a}=await I();e=_([a.skills.fundamentals.score,a.skills.linux.score,a.skills.network.score,a.skills.privesc.score,a.skills.web.score,a.skills.windows.score])}catch{e=[100,85,81,70,93,38]}finally{k.value={labels:Object.keys(x.value),datasets:[{label:"Skill Score",data:e,fill:!0,backgroundColor:"rgba(54, 162, 235, 0.2)",borderColor:"rgb(54, 162, 235)",pointBackgroundColor:"rgb(54, 162, 235)",pointBorderColor:"#fff",pointHoverBackgroundColor:"#fff",pointHoverBorderColor:"rgb(54, 162, 235)"}]},y.value=!0}};U.kL.register(U.l7,U.od,U.jn,U.Gu,U.u,U.De),U.kL.defaults.color="#BADA55",U.kL.defaults.borderColor="#adbac7";const y=(0,W.iH)(!1),k=(0,W.iH)(null),q={elements:{line:{borderWidth:3}},scales:{r:{beginAtZero:!0,min:0,max:100,backgroundColor:"rgba(0, 0, 0, 0.8)",ticks:{stepSize:20,callback:(e,a,t)=>"",backdropPadding:0}}},animation:{duration:1500,easing:"easeInOutQuart"}},H=[{name:"message",label:"Message",field:"message",align:"left",sortable:!0},{name:"committer",label:"Committer",field:"committer",align:"center"},{name:"date",label:"Date",field:"date",align:"center",sortable:!0}];return(0,s.bv)((async()=>{o(t.value),u("R3VI1E37"),g(),b("revi1337"),w()})),(e,t)=>{const r=(0,s.up)("q-img"),o=(0,s.up)("q-avatar"),d=(0,s.up)("q-item-label"),u=(0,s.up)("q-carousel-slide"),g=(0,s.up)("q-carousel"),b=(0,s.up)("q-table"),_=(0,s.up)("q-icon");return(0,s.wg)(),(0,s.iD)(s.HY,null,[P,(0,s.Wm)(g,{class:"carousel",style:{height:"auto",width:"auto"},dark:"",swipeable:"",animated:"",modelValue:i.value,"onUpdate:modelValue":t[0]||(t[0]=e=>i.value=e),autoplay:c.value,ref:"carousel",infinite:"","transition-prev":"slide-right","transition-next":"slide-left"},{default:(0,s.w5)((()=>[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(a.value,((e,a)=>((0,s.wg)(),(0,s.j4)(u,{key:a,name:a+1},{default:(0,s.w5)((()=>[(0,s._)("div",null,[(0,s.Wm)(n.Z,{id:e.id,title:e.title,content:e.content,hashtag:e.hashtag,"created-at":e.createdAt,"created-by":e.createdBy,category:e.category,shadow:!1,border:!1},{default:(0,s.w5)((({svgLink:a,timeToRead:t,content:n})=>[(0,s._)("div",R,[(0,s._)("div",J,[(0,s.Wm)(o,{size:"13rem",square:""},{default:(0,s.w5)((()=>[(0,s.Wm)(r,{src:a},null,8,["src"])])),_:2},1024)]),(0,s._)("div",V,[(0,s._)("div",$,[(0,s._)("div",null,[(0,s.Wm)(o,{size:"2rem"},{default:(0,s.w5)((()=>[(0,s.Wm)(r,{src:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZHnYW3u0Y2pKCoepqCtCchGi89SaRO4_oZWyg7ial3BmDSAqGElB_LMIqmWEIiTUCpLs&usqp=CAU"})])),_:1}),(0,s._)("span",E,(0,l.zw)(e.createdBy),1)]),(0,s._)("div",L,(0,l.zw)(e.title),1),(0,s._)("div",O,(0,l.zw)(n),1),(0,s.Wm)(d,{class:"q-mb-md"},{default:(0,s.w5)((()=>[(0,s._)("span",Y,(0,l.zw)(e.category.toUpperCase()),1),F,(0,s._)("span",G,(0,l.zw)(t),1)])),_:2},1024)])])])])),_:2},1032,["id","title","content","hashtag","created-at","created-by","category"])])])),_:2},1032,["name"])))),128))])),_:1},8,["modelValue","autoplay"]),(0,s._)("div",N,[K,(0,s._)("div",X,[(0,s._)("div",ee,[(0,s.Wm)(b,{class:"text-table",dense:"","hide-no-data":"",dark:"",flat:"",bordered:"",rows:p.value,columns:H,"row-key":"name",separator:"cell"},{top:(0,s.w5)((()=>[(0,s.Wm)(o,{"font-size":"1.8rem",size:"1.8rem",icon:"fa-brands fa-github"})])),_:1},8,["rows"])]),(0,s._)("div",ae,[(0,s._)("div",te,[(0,s._)("div",se,[y.value?((0,s.wg)(),(0,s.j4)(h,{key:0,"chart-type":"radar","chart-data":k.value,"chart-options":q},null,8,["chart-data"])):(0,s.kq)("",!0)]),(0,s._)("div",le,[(0,s._)("div",re,[(0,s._)("div",ne,[(0,s._)("div",oe,[ie,(0,s._)("div",ce,(0,l.zw)(m.value.totalUsers),1),(0,s._)("div",de,[(0,s.Wm)(_,{name:"fa-solid fa-users"}),(0,s.Uk)(" Users ")])]),(0,s._)("div",null,[isNaN(f.value)?(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("span",pe,[(0,s._)("span",ue," In the top "+(0,l.zw)(f.value)+"%",1)])),(0,s._)("div",he,(0,l.zw)(v.value.userRank),1),(0,s._)("div",me,[(0,s.Wm)(_,{name:"fa-solid fa-trophy"}),(0,s.Uk)(" Rank ")])])])]),(0,s.Wm)(T,{"user-rank":v.value.userRank,class:"q-mb-lg"},null,8,["user-rank"])])])])])])],64)}}};var ve=t(7052),be=t(1694),fe=t(1357),_e=t(335),xe=t(3115),we=t(8746),ye=t(2857),ke=t(9984),qe=t.n(ke);const He=(0,Z.Z)(ge,[["__scopeId","data-v-4c5fd1dc"]]),Se=He;qe()(ge,"components",{QCarousel:ve.Z,QCarouselSlide:be.Z,QAvatar:fe.Z,QImg:_e.Z,QItemLabel:xe.Z,QTable:we.Z,QIcon:ye.Z})}}]);