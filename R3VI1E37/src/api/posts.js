import axios from 'axios';

export async function findAllPost() {
  return axios.get('http://localhost:8080/api/posts');
}

export async function createPost(jsonData, dummy) {
  const formData = new FormData();
  formData.append('file', dummy);
  formData.append('request', jsonData);
  const headers = {
    'Content-Type': 'multipart/form-data; application/json',
    Authorization: 'test1@.com'
  };
  return axios.post('http://localhost:8080/api/posts', formData, headers);
}

export async function userLogin(jsonData) {
  return axios.post('http://localhost:8080/api/auth/login', jsonData);
}
