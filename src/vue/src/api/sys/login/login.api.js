// import {fetch} from '../../../lib/base';


export const login = params => {
  return fetch('/user/login',params);
}
