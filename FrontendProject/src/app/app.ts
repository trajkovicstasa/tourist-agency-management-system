import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIcon } from '@angular/material/icon';
import { MatNavList } from '@angular/material/list';
import { MatSidenav, MatSidenavContainer } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';

@Component({

  selector: 'app-root',
  imports: [RouterOutlet,RouterLink, MatButtonModule, MatExpansionModule, MatIcon, MatNavList, MatSidenav, MatSidenavContainer, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('FrontEndProject');
}
