import { Component } from '@angular/core';
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
import { HotelService } from '../../../services/hotel-service';
import { Hotel } from '../../../models/hotel';
import { MatTableDataSource } from '@angular/material/table';
import { Aranzman } from '../../../models/aranzman';


@Component({
  selector: 'app-hotel-dialog',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule],
  templateUrl: './hotel-dialog.component.html',
  styleUrl: './hotel-dialog.component.css',
})
export class HotelDialog {

  displayedColumns = ['id', 'naziv', 'brojZvezdica', 'opis', 'destincija'];
  dataSource!:MatTableDataSource<Aranzman>;


    flag!:number;

    constructor(private snackBar: MatSnackBar,
          private dialogRef: MatDialogRef<HotelDialog>,
          private service: HotelService,
          @Inject(MAT_DIALOG_DATA) public data: Hotel){}

    public add(): void {
      this.service.createHotel(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Hotel with naziv: ${data.naziv} has been successfully created!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during POST request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public update(): void {
      this.service.updateHotel(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Hotel with naziv: ${data.naziv} has been successfully updated!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during PUT request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public delete(): void {
      this.service.deleteHotel(this.data.id).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Hotel with naziv: ${data.naziv} has been successfully deleted!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during DELETE request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public cancel(): void {
      this.dialogRef.close();
      this.snackBar.open(`You've given up on changes!`, 'Okay', {duration: 2500});
    }
}