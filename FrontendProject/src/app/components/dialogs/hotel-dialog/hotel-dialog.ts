import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
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
import { HotelService } from '../../../services/hotel-service';
import { Hotel } from '../../../models/hotel';
import { MatTableDataSource } from '@angular/material/table';
import { Aranzman } from '../../../models/aranzman';
import { MatSelectModule } from '@angular/material/select';
import { Destinacija } from '../../../models/destinacija';
import { DestinacijaService } from '../../../services/destinacija-service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-hotel-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, MatSelectModule, MatSnackBarModule, FormsModule],
  templateUrl: './hotel-dialog.html',
  styleUrl: './hotel-dialog.css',
})
export class HotelDialog implements OnInit {

  flag!: number;
  destinacija!: Destinacija[];

  destinacije: Destinacija[] = [];

  constructor(
    private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<HotelDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private service: HotelService,
    private destinacijaService: DestinacijaService
  ) {}

  ngOnInit(): void {
    this.destinacijaService.getAllDestinacijas().subscribe({
      next: (data) => {
        this.destinacije = data;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  public add(): void {
    this.service.createHotel(this.data).subscribe({
      next: (data) => {
        this.dialogRef.close(1);

        this.snackBar.open(
          `Hotel with naziv: ${data.naziv} has been successfully created`,
          'Okay',
          { duration: 2500 }
        );
      },

      error: (err) => {
        this.snackBar.open(
          'There was an error during POST request',
          'Okay',
          { duration: 2500 }
        );
      }
    });
  }

  public update(): void {
    this.service.updateHotel(this.data).subscribe({
      next: (data) => {
        this.dialogRef.close(1);

        this.snackBar.open(
          `Hotel with naziv: ${data.naziv} has been successfully updated`,
          'Okay',
          { duration: 2500 }
        );
      },

      error: (err) => {
        this.snackBar.open(
          'There was an error during PUT request',
          'Okay',
          { duration: 2500 }
        );
      }
    });
  }

  public delete(): void {
    this.service.deleteHotel(this.data.id).subscribe({
      next: () => {
        this.dialogRef.close(1);

        this.snackBar.open(
          'Hotel has been successfully deleted',
          'Okay',
          { duration: 2500 }
        );
      },

      error: (err) => {
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

  public compare(a: { id: number } | null, b: { id: number } | null): boolean {
    return a && b ? a.id === b.id : a === b;
  }
}
