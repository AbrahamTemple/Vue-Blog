import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://8.135.100.252:8096',
  timeout: 3000,
});

export default instance
