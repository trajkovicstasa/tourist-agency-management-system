import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AranzmanComponent } from '../aranzman-component/aranzman-component';
import { TuristickaAgencija } from '../../../models/turistickaAgencija';
import { TuristickaAgencijaService } from '../../../services/turistickaAgencija-service';
import { TuristickaAgencijaDialog } from '../../dialogs/turistickaAgencija-dialog/turistickaAgencija-dialog';
import { HotelComponent } from '../hotel-component/hotel-component';
import { CommonModule } from '@angular/common';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-turisticka-agencija-component',
  imports: [
    CommonModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    HotelComponent,
    AranzmanComponent
  ],

  

  templateUrl: './turisticka-agencija-component.html',
  styleUrl: './turisticka-agencija-component.css',


})
export class TuristickaAgencijaComponent implements OnInit, AfterViewInit {
  displayedColumns = ['id', 'naziv', 'adresa', 'kontakt', 'actions'];

  dataSource!: MatTableDataSource<TuristickaAgencija>;
  parentSelectedTuristickaAgencija?: TuristickaAgencija;

  activeChild: 'hotel' | 'aranzman' = 'aranzman';

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: TuristickaAgencijaService,
    public dialog: MatDialog
  ){}

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit(): void {
    this.assignTableControls();
  }

  private assignTableControls(): void {
    if (this.dataSource) {
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    }
  }

   public loadData(): void {
    this.service.getAllTuristickaAgencijas().subscribe({

      next: (data) => {

        this.dataSource = new MatTableDataSource<TuristickaAgencija>(data);
        this.assignTableControls();

        
      },

      error: (err) => {

        console.log(err);
      }
      
      
    });
  }

  public selectRow(row: TuristickaAgencija): void {
    this.parentSelectedTuristickaAgencija = row;

    this.activeChild = 'aranzman';
  }

  public setActiveChild(child: 'hotel' | 'aranzman'): void {
    this.activeChild = child;
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
