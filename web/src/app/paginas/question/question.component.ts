import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { AnswerI } from 'src/app/models/answer-i';
import { NewQuestion } from 'src/app/models/newQuestion';
import { QuestionService } from 'src/app/Service/question.service';
import { TokenServiceService } from 'src/app/Service/tokenService/token-service.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
  providers: [MessageService],
})
export class QuestionComponent implements OnInit {
  answers: AnswerI[] | undefined;
  newQuestion: NewQuestion;
  question?: string;
  type?: string;
  category?: string;

  public form: FormGroup = this.formBuilder.group({
    question: ['', [Validators.required]],
    category: ['', [Validators.required]],
    type: ['', [Validators.required]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private modalService: NgbModal,
    private services: QuestionService,
    private messageService: MessageService,
    private tokenService: TokenServiceService
  ) {
    this.newQuestion = {
      question: '',
      category: '',
      type: '',
      userId: this.tokenService.getUserName(),
    };
  }

  ngOnInit(): void {}

  openVerticallyCentered(content: any) {
    this.modalService.open(content, { centered: true });
  }

  saveQuestion() {
    this.modalService.dismissAll();
    this.services.saveQuestion(this.newQuestion).subscribe(
      (data) => {},
      (err: HttpResponse<string>) => {
        if (err.status === 200) {
          this.messageService.add({
            severity: 'success',
            summary: 'Gracias por preguntar ',
            detail: 'Pregunta guardada',
          }),
            setTimeout(() => {
              window.location.reload();
            }, 3000);
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Rectifique los datos',
            detail: 'Usuario no registrado',
          });
        }
      }
    );
  }
}
