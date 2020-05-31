import { OnInit, Component } from "@angular/core";
import { Exposition } from "../../models/exposition";
import { UserService } from "../../services/user.service";
import { ParticipationService } from "../../services/participation.service";
import { ActivatedRoute } from "@angular/router";
import { BilletService } from "../../services/billet.service";
import { User } from "../../models/user";
import { Billet } from "../../models/billet";



@Component({
  selector: 'app-paiementaccepte',
  templateUrl: './paiementaccepte.component.html',
  styleUrls: ['./paiementaccepte.component.css']
})
export class PaiementaccepteComponent implements OnInit {

  user:User;
  email:string;
  envoieBillet() {




    this.user=this.userService.currentUserValue;
    this.email=this.userService.currentUserValue.adresse_email;
    alert("user email "+this.email);





    











  










    this.exposition=JSON.parse(localStorage.getItem("exposition"));
   
    //localStorage.setItem('currentUser', JSON.stringify(response));
    this.billet=new Billet();
    this.billet.isUtilise=false;
    this.billet.exposition=this.exposition;
    this.billetService.envoyerBillet(this.billet).subscribe(data => {


      //console.log("billet creer "+data.id);
      this.billetService.envoieQr(Number.parseInt(data.id),this.user.adresse_email).subscribe(data=>{


        

      },err=>{

        
      });

     

    }, err => {
      alert("error");
      //this.errorMessage = "pseudo ou mot de passe inccorecte";
    });

    //this.billetService.envoyerBillet()
  }

  exposition:Exposition;

  currentUser: User;

  public errorMessage:string;
  public InfoMessage:string;
  constructor(private userService: UserService,private participationService:ParticipationService,
     private route: ActivatedRoute,private userServices:UserService,
     private billetService:BilletService) { 
       

      this.envoieBillet();
   

  }


  billet:Billet;
  ngOnInit(): void {
    

  }
}
