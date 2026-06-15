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


@Component({
  selector: 'app-aranzman-dialog',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule,
    MatCheckboxModule, MatDatepickerModule, MatNativeDateModule, MatSelectModule
  ],
 
  templateUrl: './aranzman-dialog.component.html',
  styleUrl: './aranzman-dialog.component.css',
})
export class AranzmanDialog implements OnInit {

    flag!:number;
    hoteli!:Hotel[];

    constructor(private snackBar: MatSnackBar,
          private dialogRef: MatDialogRef<AranzmanDialog>,
          private service: AranzmanService,
          @Inject(MAT_DIALOG_DATA) public data: Aranzman,
          private aranzmanService: AranzmanService){}

    ngOnInit(): void {
      this.aranzmanService.getAllAranzmans().subscribe(
        (data) => this.hoteli = data
      )
    }
    public add(): void {
      this.service.createAranzman(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Aranzman with placeno: ${data.placeno} has been successfully created!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during POST request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public update(): void {
      this.service.updateAranzman(this.data).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Aranzman with ukupna cena: ${data.ukupnaCena} has been successfully updated!`, 'Okay', {duration: 2500});
        },
        error: (err) => {
          this.snackBar.open('Something went wrong during PUT request', 'Okay', {duration: 2500});
          console.log(err.message);
        }}
      )
    }

    public delete(): void {
      this.service.deleteAranzman(this.data.id).subscribe(
        {next: (data) => {
          this.dialogRef.close(1);
          this.snackBar.open(`Aranzman with ukupna cena: ${data.ukupnaCena} has been successfully deleted!`, 'Okay', {duration: 2500});
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

    public compare(a:any, b:any){
      return a.id == b.id;
    }
}