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


@Component({ // angular dekorator, govori angularu da je klasa ispod komponenta i daje joj podesavanja
  selector: 'app-hotel-dialog', // definise naziv komponente u htmlu <app-hotel-dialog> tako se poziva taj dijalog u htmlu
  standalone: true, // komponenta je samostalna
  imports: [CommonModule, MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, MatSelectModule, MatSnackBarModule, FormsModule],
  // spisak angular material modula 
  templateUrl: './hotel-dialog.html',// povezuje typescript klasu sa html fajlom dijaloga
  styleUrl: './hotel-dialog.css',
})
export class HotelDialog implements OnInit {
  // implements OnInit znaci da klasa koristi angular zivotni ciklus ngOnInit()

  flag!: number;// flag odredjuje rezim rada dijaloga
  destinacija!: Destinacija[];//deklaracija promenljive koja bi sadrzala niz destinacija

  destinacije: Destinacija[] = []; // pravi niz koji je na pocetku prazak
  // kada backend vrati destinacije one se smestaju u ovaj niz
  // html zatim prolazi kroz taj niz i prikazuje opcije u padajucoj listi

  constructor(// poziva se kad angular napravi komponentu
    // ovde angular ubacuje potrebne servise i objekte kroz dependency injection
    private snackBar: MatSnackBar,//snackBar sluzi za kratke poruke korisniku i koriste se nakon uspesnog doavanja izmene ili greske
    public dialogRef: MatDialogRef<HotelDialog>,//dialogRef je trenutno otvoren dijalog
    @Inject(MAT_DIALOG_DATA) public data: any,
    // uzima podatke koji su poslati prilikom otvaranja dijaloga, ti podaci su objekat hotela
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
  // metoda koja poredi dve destinacije
  // ako i a i b postoje poredi njihove id vrednosti
  // vraca true ako su idjevi isti
  // ako je jedan ili oba objekta null proverava dal su oba ista
  // tako angular pravilno prikaze prethodno odabranu destinaciju pilikom izmene hotela
}
