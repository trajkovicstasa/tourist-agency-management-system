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
import { HotelDialog } from '../../dialogs/hotel-dialog/hotel-dialog.component';

@Component({
  selector: 'app-hotel',
  imports: [
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule,
    MatButtonModule
  ],
  templateUrl: './hotel.html',
  styleUrl: './hotel.css',
})

export class HotelComponent implements OnInit, OnChanges, AfterViewInit {

  displayedColumns = ['id', 'naziv', 'brojZvezdica', 'opis', 'destinacija', 'actions'];

  dataSource = new MatTableDataSource<Hotel>([]);

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  @Input()
  childSelectedDestinacija!: Destinacija;

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
      next: (data: Hotel[]) => {
        if (this.childSelectedDestinacija) {
          data = data.filter(
            (hotel: Hotel) => hotel.destinacija?.id === this.childSelectedDestinacija.id
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
    brojZvezdica?: number,
    opis?: string,
    destinacija?: Destinacija
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
