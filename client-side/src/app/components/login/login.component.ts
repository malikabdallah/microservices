import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  errorMessage: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    if(this.userService.currentUserValue){
      this.router.navigate(['/home']);
      return;
    }
  }

  login(){
    this.userService.login(this.user).subscribe(data => {

 
      this.router.navigate(['/home']);
    }, err => {
      this.errorMessage = "pseudo ou mot de passe inccorecte";
    });
  }

}