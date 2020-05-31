import { Component, OnInit } from '@angular/core';
import {Exposition} from '../../models/exposition';

import {ActivatedRoute, Router} from '@angular/router';
import { ExpositionService } from '../../services/exposition.service';
import { comments } from '../../models/comments';
import { CommentService } from '../../services/comment.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-exposition-details',
  templateUrl: './exposition-details.component.html',
  styleUrls: ['./exposition-details.component.css']
})
export class ExpositionDetailsComponent implements OnInit {
  
  
  courseId: number;
  expositionCourant: Exposition;

  
  listCommentaire:Array<Comment>;
  studentList: Array<string>;
  exposition:Exposition ;
  expositionId:string;

  constructor(private UserService:UserService, private router: Router,
    private commentService:CommentService,private expositionService:ExpositionService, private route: ActivatedRoute) { 
  
    this.expositionCourant = JSON.parse(localStorage.getItem('currentCourse'));
    
    console.log("courant "+this.expositionCourant);
  }

  


  consulter(){
    this.router.navigate(['/oeuvres/exposition',this.courseId]);

    alert("consulter");
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.has('id')) {
        this.courseId = Number.parseInt(params.get('id'));
        this.expositionId=params.get("id");
         this.findCommentaireOfexpostion();
      }
    });
  }
  findCommentaireOfexpostion() {
    this.expositionService.findExpositionCommentes(this.courseId).subscribe(data => {
      this.listCommentaire = data;
      console.log("commentaire "+this.listCommentaire);
    });  }


    message:string;

    comment:comments;

    enregistrerComentaire(){
      this.comment=new comments();
      this.comment.content=this.message;
      var user:User;
      user=this.UserService.currentUserValue;
      
      this.comment.username=user.username;
     



        this.commentService.enroll(this.expositionId,this.comment).subscribe(resp => {
          if(resp){
            this.findCommentaireOfexpostion();
           
          }else{
              alert("refuse");
          }
        })
      
    }
  
    
  
  
}
