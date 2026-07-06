import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { Inject } from '@angular/core';
import { AranzmanService } from '../../../services/aranzman-service';
import { Aranzman } from '../../../models/aranzman';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { Hotel } from '../../../models/hotel';
import { TuristickaAgencija } from '../../../models/turistickaAgencija';
import { HotelService } from '../../../services/hotel-service';
import { TuristickaAgencijaService } from '../../../services/turistickaAgencija-service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-aranzman-dialog',
  imports: [CommonModule, MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule,
    MatCheckboxModule, MatDatepickerModule, MatNativeDateModule, MatSelectModule
  ],
 
  templateUrl: './aranzman-dialog.html',
  styleUrl: './aranzman-dialog.css',
})
export class AranzmanDialog {

  flag!: number;

  hoteli: Hotel[] = [];
  agencije: TuristickaAgencija[] = [];

  constructor(
    private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<AranzmanDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private service: AranzmanService,
    private hotelService: HotelService,
    private agencijaService: TuristickaAgencijaService
  ) {}

  ngOnInit(): void {

    this.hotelService.getAllHotels().subscribe({
      next: (data) => {
        this.hoteli = data;
      }
    });

    this.agencijaService.getAllTuristickaAgencijas().subscribe({
      next: (data) => {
        this.agencije = data;
      }
    });
  }

  public compare(first: { id: number } | null, second: { id: number } | null): boolean {
    return first && second ? first.id === second.id : first === second;
  }

  public add(): void {
    this.service.createAranzman(this.data).subscribe({
      next: (data) => {
        this.dialogRef.close(1);

        this.snackBar.open(
          `Aranzman ${data.ukupnaCena} ${data.placeno} has been successfully created`,
          'Okay',
          { duration: 2500 }
        );
      },
      error: () => {
        this.snackBar.open(
          'There was an error during POST request',
          'Okay',
          { duration: 2500 }
        );
      }
    });
  }

  public update(): void {
    this.service.updateAranzman(this.data).subscribe({
      next: (data) => {
        this.dialogRef.close(1);

        this.snackBar.open(
          `Aranzman ${data.ukupnaCena} ${data.placeno} has been successfully updated`,
          'Okay',
          { duration: 2500 }
        );
      },
      error: () => {
        this.snackBar.open(
          'There was an error during PUT request',
          'Okay',
          { duration: 2500 }
        );
      }
    });
  }

  public delete(): void {
    this.service.deleteAranzman(this.data.id).subscribe({
      next: () => {
        this.dialogRef.close(1);

        this.snackBar.open(
          'Aranzman has been successfully deleted',
          'Okay',
          { duration: 2500 }
        );
      },
      error: () => {
        this.snackBar.open(
          'There was an error during DELETE request',
          'Okay',
          { duration: 2500 }
        );
      }
    });
  }

  public cancel(): void {
    this.dialogRef.close();

    this.snackBar.open(
      `You've given up on changes!`,
      'Okay',
      { duration: 2500 }
    );
  }
}
