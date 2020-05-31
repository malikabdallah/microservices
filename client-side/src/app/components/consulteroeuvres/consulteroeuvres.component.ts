import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Oeuvre } from '../../models/oeuvre';
import { OeuvresService } from '../../services/oeuvres.service';

@Component({
  selector: 'app-consulteroeuvres',
  templateUrl: './consulteroeuvres.component.html',
  styleUrls: ['./consulteroeuvres.component.css']
})
export class ConsulteroeuvresComponent implements OnInit {

  constructor(private route: ActivatedRoute,private oeuvreservice:OeuvresService) { }

  id:string;
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.has('id')) {
        //alert("exposition "+params.get("id"));
      this.id=  params.get("id");
      }
    });

    this.findAllCourses();
  }





  
  expositionList:Array<Oeuvre>;


 
 
  findAllCourses() {
  
    this.oeuvreservice.findAllExposition(this.id).subscribe(data=>{
      this.expositionList=data
    })
   
  }


}
