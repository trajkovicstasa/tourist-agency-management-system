import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AranzmanService } from '../../../services/aranzman-service';
import { Aranzman } from '../../../models/aranzman';
import { AranzmanDialog } from '../../dialogs/aranzman-dialog/aranzman-dialog';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { TuristickaAgencija } from '../../../models/turistickaAgencija';
import { Destinacija } from '../../../models/destinacija';

@Component({
  selector: 'app-aranzman-component',
  standalone: true,
  imports: [
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatToolbarModule,
    MatDialogModule
  ],
  templateUrl: './aranzman-component.html',
  styleUrl: './aranzman-component.css',
})

export class AranzmanComponent implements OnInit, OnChanges, AfterViewInit {

  displayedColumns = [
    'id',
    'ukupnaCena',
    'placeno',
    'datumRealizacije',
    'hotel',
    'agencija',
    'actions'
  ];

  dataSource = new MatTableDataSource<Aranzman>([]);

  @Input()
  childSelectedDestinacija?: Destinacija;

  @Input()
  childSelectedTuristickaAgencija?: TuristickaAgencija;

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: AranzmanService,
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
    if (
      (changes['childSelectedDestinacija'] && !changes['childSelectedDestinacija'].firstChange) ||
      (changes['childSelectedTuristickaAgencija'] && !changes['childSelectedTuristickaAgencija'].firstChange)
    ) {
      this.loadData();
    }
  }

  public loadData(): void {
    this.service.getAllAranzmans().subscribe({
      next: (data) => {
        let filtered = data;

        if (this.childSelectedDestinacija) {
          filtered = filtered.filter(
            p => p.hotel?.destinacija?.id === this.childSelectedDestinacija?.id
          );
        }

        if (this.childSelectedTuristickaAgencija) {
          filtered = filtered.filter(
            p => p.agencija?.id === this.childSelectedTuristickaAgencija?.id
          );
        }

        this.dataSource.data = filtered;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },

      error: (err) => {
        console.log(err);
      }
    });
  }

  public openDialog(
    flag: number,
    id?:number,
    ukupnaCena?:number,
    placeno?:boolean,
    datumRealizacije?:Date,
    hotel?:any,
    agencija?:any,
  ): void {

    const ref = this.dialog.open(AranzmanDialog, {
      data: {
        id,
        ukupnaCena,
        placeno,
        datumRealizacije,
        hotel,
        agencija
      }
    });

    ref.componentInstance.flag = flag;

    ref.afterClosed().subscribe((result) => {
      if (result === 1) {
        this.loadData();
      }
    });
  }
}
