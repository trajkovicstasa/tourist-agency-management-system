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
import { TuristickaAgencijaService } from '../../../services/turistickaAgencija-service';
import { TuristickaAgencija } from '../../../models/turistickaAgencija';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-turistickaAgencija-dialog',
  standalone: true,
  imports: [CommonModule,MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule],
  templateUrl: './turistickaAgencija-dialog.html',
  styleUrl: './turistickaAgencija-dialog.css',
})
export class TuristickaAgencijaDialog {

    flag!:number;

    constructor(private snackBar: MatSnackBar,
          private dialogRef: MatDialogRef<TuristickaAgencijaDialog>,
          private service: TuristickaAgencijaService,
          @Inject(MAT_DIALOG_DATA) public data: TuristickaAgencija){}

    public add(): void {
      this.service.createTuristickaAgencija(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Turisticka agencija with naziv: ${data.naziv} has been successfully created!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during POST request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public update(): void {
      this.service.updateTuristickaAgencija(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Turisticka agencija with naziv: ${data.naziv} has been successfully updated!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during PUT request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public delete(): void {
      const naziv = this.data.naziv;

      this.service.deleteTuristickaAgencija(this.data.id).subscribe(
        {next: () => {
          this.dialogRef.close(1);
          this.snackBar.open(`Turisticka agencija with naziv: ${naziv} has been successfully deleted!`, 'Okay', {duration: 2500});
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
