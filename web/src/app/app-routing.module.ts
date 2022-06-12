import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnswerComponent } from './paginas/answer/answer.component';
import { QuestionComponent } from './paginas/question/question.component';
import { RequestionComponent } from './paginas/requestion/requestion.component';
import { LoginComponent } from './persona/login/login.component';
import { PreguntasComponent } from './persona/preguntas/preguntas.component';
import { RecuperarPasswordComponent } from './persona/recuperar-password/recuperar-password.component';
import { RegistroComponent } from './persona/registro/registro.component';
import { UpdateComponent } from './persona/update/update.component';

const routes: Routes = [
  { path: 'update', component: UpdateComponent },
  { path: 'login', component: LoginComponent },
  { path: 'reset', component: RecuperarPasswordComponent },
  { path: 'preguntas', component: PreguntasComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'answer', component: AnswerComponent },
  { path: 'question/:id', component: RequestionComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'preguntas' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
