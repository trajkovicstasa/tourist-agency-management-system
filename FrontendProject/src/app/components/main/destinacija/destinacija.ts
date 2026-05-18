import { Component, OnInit } from '@angular/core';
import { DestinacijaService } from '../../../services/destinacija-service';
import { Destinacija } from '../../../models/destinacija';

@Component({
  selector: 'app-destinacija',
  imports: [],
  templateUrl: './destinacija.html',
  styleUrl: './destinacija.css',
})
export class DestinacijaComponent implements OnInit {

  destinacije:Destinacija[] = [];

  constructor(private service:DestinacijaService){}

  ngOnInit(): void {
    this.service.getAllDestinacijas().subscribe(
      {next: (data) => this.destinacije = data,
        error: (err) => console.log(err)
      }
    )
    
  }
}
