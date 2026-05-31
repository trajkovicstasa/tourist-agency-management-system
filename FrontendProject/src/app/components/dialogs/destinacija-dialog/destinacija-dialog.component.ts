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
import { DestinacijaService } from '../../../services/destinacija-service';
import { Destinacija } from '../../../models/destinacija';

@Component({
  selector: 'app-destinacija-dialog',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule],
  templateUrl: './destinacija-dialog.component.html',
  styleUrl: './destinacija-dialog.component.css',
})
export class DestinacijaDialog {

    flag!:number;

    constructor(private snackBar: MatSnackBar,
          private dialogRef: MatDialogRef<DestinacijaDialog>,
          private service: DestinacijaService,
          @Inject(MAT_DIALOG_DATA) public data: Destinacija){}

    public add(): void {
      this.service.createDestinacija(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Destinacija with mesto: ${data.mesto} has been successfully created!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during POST request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public update(): void {
      this.service.updateDestinacija(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Destinacija with mesto: ${data.mesto} has been successfully updated!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during PUT request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public delete(): void {
      this.service.deleteDestinacija(this.data.id).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Destinacija with mesto: ${data.mesto} has been successfully deleted!`, 'Okay', {duration: 2500});
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