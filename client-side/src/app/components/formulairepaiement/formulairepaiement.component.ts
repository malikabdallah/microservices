import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { ExpositionService } from 'src/app/services/exposition.service';
import { Exposition } from 'src/app/models/Exposition';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-formulairepaiement',
  templateUrl: './formulairepaiement.component.html',
  styleUrls: ['./formulairepaiement.component.css']
})
export class FormulairepaiementComponent implements OnInit {

  constructor(private http: HttpClient,private router: Router,private route: ActivatedRoute,private expositionService:
    ExpositionService) { }

    exposition:Exposition;
    id:string;
    list:Array<any>;
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.has('id')) {
        this.id=(params.get('id'));
      //alert("id recherche "+this.id);
      
      this.expositionService.trouverExpositionParId(Number.parseInt(this.id)).subscribe(data => {
        this.exposition = data;
      });
       
       console.log("exposition "+this.exposition);
    
      }
    });
  }



  chargeCreditCard() {
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
     // card: form.cardNumber.value,
     number:form.cardNumber.value,
      exp_month: form.expMonth.value,
      exp_year: form.expYear.value,
      cvc: form.cvc.value
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token);
      } else {
        console.log(response.error.message);
      }
    });
  }


  
  chargeCard(token: string) {
    alert("chargement");
    var headers=new HttpHeaders();

   // var headers = new HttpHeaders({'token': token, 'amount': 100});
    headers = headers.append('Accept', 'application/json');

    this.http.post('http://localhost:8003/payment/charge?tokenn='+token, {}, {headers: headers})
      .subscribe(resp => {
        if(resp){
          localStorage.setItem('exposition', JSON.stringify(this.exposition));

        

          this.router.navigate(["/succespaiement"])
        }else{
            alert("refuse");
        }
      })
    }
  


}
