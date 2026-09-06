import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { DestinacijaService } from '../../../services/destinacija-service';
import { Destinacija } from '../../../models/destinacija';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog } from '@angular/material/dialog';
import { DestinacijaDialog } from '../../dialogs/destinacija-dialog/destinacija-dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { CommonModule } from '@angular/common';
import { HotelComponent } from '../hotel-component/hotel-component';

@Component({
  selector: 'app-destinacija-component',
  imports: [
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule, 
    MatToolbarModule, 
    MatButtonModule,
    CommonModule,
    HotelComponent],
  templateUrl: './destinacija-component.html',
  styleUrl: './destinacija-component.css',
})
export class DestinacijaComponent implements OnInit, AfterViewInit {

  displayedColumns = ['id', 'mesto', 'drzava', 'opis', 'actions'];
  dataSource = new MatTableDataSource<Destinacija>([]);
  selectedDestinacija?: Destinacija;

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  

  constructor(
      private service:DestinacijaService,
      private dialog: MatDialog
  ){}
  
  ngOnInit(): void {
    
    
       this.loadData();
      }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  public loadData(): void {
    this.service.getAllDestinacijas().subscribe({
      next: (data) => {
        this.dataSource.data = data;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
    
      },
      error: (err) => console.log(err)
    });
  }

  public selectRow(row: Destinacija): void {
    this.selectedDestinacija = row;
  }

  public openDialog(
    flag: number,
    id?:number, 
    mesto?: string, 
    drzava?: string, 
    opis?: string 
  ): void {
    const ref = this.dialog.open(DestinacijaDialog, {data: {id, mesto, drzava, opis}});
    ref.componentInstance.flag = flag;

    ref.afterClosed().subscribe((result) => {
      if(result === 1){
        this.loadData();
      }
    
    
  })

}

}
