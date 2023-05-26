import axios from 'axios';

export async function joinUser(jsonData) {
  return axios.post('/api/user/join', jsonData);
}
