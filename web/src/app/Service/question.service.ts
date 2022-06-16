import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { QuestionI } from '../models/question-i';
import { AnswerI } from '../models/answer-i';
import { TokenServiceService } from './tokenService/token-service.service';
import { NewQuestion } from '../models/newQuestion';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  push(arg0: string) {
    throw new Error('Method not implemented.');
  }
  private url: string = 'http://localhost:8080/';

  constructor(
    private http: HttpClient,
    private tokenService: TokenServiceService
  ) {}

  sendHeaders() {
    let headers = new HttpHeaders();
    headers = headers.append('content-type', 'application/json');
    headers = headers.append('Access-Control-Allow-Origin', '*');
    headers = headers.append(
      'Access-Control-Allow-Headers',
      'Origin, Content-Type, X-Auth-Token'
    );
    headers = headers.append(
      'Access-Control-Allow-Methods',
      'GET,POST,OPTIONS,DELETE,PUT'
    );
    headers = headers.append(
      'Authorization',
      'Bearer ' + this.tokenService.getToken()
    );
    return headers;
  }

  getAllQuestions() {
    return this.http.get<Array<QuestionI>>(
      `${environment.urlBaseLocal}${environment.getAllQuestions}`,
      { headers: this.sendHeaders() }
    );
  }

  getPage(page: number): Observable<QuestionI[]> {
    let direction = this.url + 'pagination/' + page;
    return this.http.get<QuestionI[]>(direction);
  }

  getAnswer(id: any) {
    return this.http
      .get<QuestionI>(
        `${environment.urlBaseLocal}${environment.getAnswer}` + id,
        { headers: this.sendHeaders() }
      )
      .pipe(map((r) => r.answers));
  }

  getQuestion(id: string) {
    return this.http
      .get<QuestionI>(
        `${environment.urlBaseLocal}${environment.getAnswer}` + id,
        { headers: this.sendHeaders() }
      )
  }

  getTotalPages(): Observable<number> {
    let direction = this.url + 'totalPages';
    return this.http.get<number>(direction);
  }

  saveQuestion(question: NewQuestion) {
    return this.http.post<NewQuestion>(
      `${environment.urlBaseLocal}${environment.createQuestions}`,
      question,
      { headers: this.sendHeaders() }
    );
  }

  saveAnswer(answer: AnswerI): Observable<any> {
    return this.http.post<any>(
      `${environment.urlBaseLocal}${environment.saveAnswer}`,
      answer,
      { headers: this.sendHeaders() }
    );
  }

  editQuestion(question: QuestionI): Observable<any> {
    let direction = this.url + 'update';
    return this.http.post<any>(direction, question);
  }
}
