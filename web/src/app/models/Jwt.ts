export interface Jwt {
  token: string;
  bearer: string;
  userName: string;
  authorities: string[];
}
