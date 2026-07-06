import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { Hotel } from '../../../models/hotel';
import { Destinacija } from '../../../models/destinacija';
import { HotelService } from '../../../services/hotel-service';
import { HotelDialog } from '../../dialogs/hotel-dialog/hotel-dialog';

@Component({
  selector: 'app-hotel-component',
  standalone: true,
  imports: [
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule
  ],
  templateUrl: './hotel-component.html',
  styleUrl: './hotel-component.css',
})

export class HotelComponent implements OnInit, OnChanges, AfterViewInit {

  displayedColumns = ['id', 'naziv', 'brojZvezdica', 'opis', 'destinacija', 'actions'];

  dataSource = new MatTableDataSource<Hotel>([]);

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  @Input()
  childSelectedDestinacija?: Destinacija;

  

  constructor(
    private service: HotelService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }


  public loadData(): void {
    this.service.getAllHotels().subscribe({
      next: (data) => {
        if (this.childSelectedDestinacija) {
          const selectedDestinacija = this.childSelectedDestinacija;
          data = data.filter(
            o => o.destinacija?.id === selectedDestinacija.id
          );
        }

        this.dataSource.data = data;
      },

      error: (err) => {
        console.log(err);
      }
    });
  }

  public openDialog(
  flag: number,
  id?: number,
  naziv?: string,
  brojZvezdica?: string,
  opis?: string,
  destinacija?: any
): void {

  if (this.childSelectedDestinacija) {
    destinacija = this.childSelectedDestinacija;
  }

  const ref = this.dialog.open(HotelDialog, {
    data: { id, naziv, brojZvezdica, opis, destinacija }
  });

  ref.componentInstance.flag = flag;

  ref.afterClosed().subscribe((result) => {
    if (result === 1) {
      this.loadData();
    }
  });
}

}