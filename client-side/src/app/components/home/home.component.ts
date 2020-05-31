import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import { Exposition } from '../../models/exposition';
import { ExpositionService } from '../../services/exposition.service';
import { CommentService } from '../../services/comment.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  expositionList:Array<Exposition>;
  errorMessage: string;
  currentUser: User;

  constructor(private commentService:CommentService,
    private expositionService:ExpositionService,private userService: UserService, private router: Router) 
  {
    this.currentUser = this.userService.currentUserValue;
  }

  ngOnInit() {
    this.findAllCourses();
  }

  findAllCourses() {
  
    this.expositionService.findAllExposition().subscribe(data=>{
      this.expositionList=data
    })
   
  }

  comment:Observable<Comment>;

  paiement(exposition: Exposition) {
    if(!this.currentUser){
      this.errorMessage = "Vous devez etre connecte pour continuer";
      return;
    }
    
   this.router.navigate(['/paiement',exposition.id]);
  }

  detail(exposition: Exposition) {
    localStorage.setItem("currentCourse",JSON.stringify(exposition));
    this.router.navigate(['/detail',exposition.id]);
 
  }
}
