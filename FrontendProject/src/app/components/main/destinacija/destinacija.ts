import { Component, OnInit } from '@angular/core';
import { DestinacijaService } from '../../../services/destinacija-service';
import { Destinacija } from '../../../models/destinacija';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog } from '@angular/material/dialog';
import { DestinacijaDialog } from '../../dialogs/destinacija-dialog/destinacija-dialog.component';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-destinacija',
  imports: [MatTableModule, MatIconModule, MatToolbarModule, MatButtonModule],
  templateUrl: './destinacija.html',
  styleUrl: './destinacija.css',
})
export class DestinacijaComponent implements OnInit {

  displayedColumns: string[] = ['id', 'mesto', 'drzava', 'opis', 'actions'];
  dataSource: MatTableDataSource<Destinacija> = new MatTableDataSource<Destinacija>([]);

  destinacije:Destinacija[] = [];

  constructor(private service:DestinacijaService, private dialog: MatDialog){}
  
  ngOnInit(): void {
    
    
       this.loadData();
      }

      public loadData(): void {
    this.service.getAllDestinacijas().subscribe({
      next: (data) => {
        this.dataSource.data = data;
        console.log(data);
      },
      error: (err) => console.log(err)
    });
  }

  public openDialog(flag: number, id?:number, mesto?: string, drzava?: string, opis?: string ): void {
    const ref = this.dialog.open(DestinacijaDialog, {data: {id, mesto, drzava, opis}});
    ref.componentInstance.flag = flag;

    ref.afterClosed().subscribe(response => {
      if(response === 1){
        this.loadData();
      }
    
    
  })

}

}
