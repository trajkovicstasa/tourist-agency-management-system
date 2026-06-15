import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AranzmanComponent } from '../aranzman/aranzman';
import { TuristickaAgencija } from '../../../models/turistickaAgencija';
import { TuristickaAgencijaService } from '../../../services/turistickaAgencija-service';
import { TuristickaAgencijaDialog } from '../../dialogs/turistickaAgencija-dialog/turistickaAgencija-dialog.component';

@Component({
  selector: 'app-turisticka-agencija',
  imports: [
    MatTableModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    AranzmanComponent
  ],

  

  templateUrl: './turisticka-agencija.html',
  styleUrl: './turisticka-agencija.css',


})
export class TuristickaAgencijaComponent implements OnInit{
  displayedColumns = ['id', 'naziv', 'adresa', 'kontakt', 'actions'];

  dataSource!: MatTableDataSource<TuristickaAgencija>;
  parentSelectedTuristickaAgencija!: TuristickaAgencija;

  constructor(
    private service: TuristickaAgencijaService,
    public dialog: MatDialog
  ){}

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void{
    this.service.getAllTuristickaAgencijas().subscribe({

      next: (data) => {

        this.dataSource = new MatTableDataSource(data);

        console.log(data);
      },

      error: (err) => {

        console.log(err);
      }
      
      
    });
  }

  public selectRow(row: TuristickaAgencija): void {
    this.parentSelectedTuristickaAgencija = row;
  }

  public openDialog(
  flag: number,
  id?: number,
  naziv?: string,
  adresa?: string,
  kontakt?: number
): void {
  const ref = this.dialog.open(TuristickaAgencijaDialog, {
    data: { id, naziv, adresa, kontakt }
  });

  ref.componentInstance.flag = flag;

  ref.afterClosed().subscribe((result) => {
    if (result === 1) {
      this.loadData();
    }
  });
}
}