import axios from 'axios';

export async function findAllPost() {
  return axios.get('/api/posts');
}

export async function createPost(jsonData, dummy) {
  const formData = new FormData();
  formData.append('file', dummy);
  formData.append('request', jsonData);
  const headers = {
    'Content-Type': 'multipart/form-data; application/json',
    Authorization: 'test1@.com'
  };
  return axios.post('/api/posts', formData, headers);
}
