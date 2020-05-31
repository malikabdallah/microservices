import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  errorMessage: string;
  success :string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    if(this.userService.currentUserValue) {
      this.router.navigate(['/home']);
      return;
    }
  }

  inscription(){
    this.userService.register(this.user).subscribe(data => {

      this.success="votre compte a ete creer avec succes";
    }, err => {
      if(!err || err.status !== 409) {
        this.errorMessage = "Erreur " + err;
      }else {
        this.errorMessage = "Le pseudo est deja pris";
      }
    });
  }

}
