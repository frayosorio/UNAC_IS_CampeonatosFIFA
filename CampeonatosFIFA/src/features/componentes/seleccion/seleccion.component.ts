import { Component, OnInit } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { ColumnMode, NgxDatatableModule } from '@swimlane/ngx-datatable';
import { Seleccion } from '../../../shared/entidades/Seleccion';
import { SeleccionService } from '../../../core/servicios/seleccion.service';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { SeleccionEditarComponent } from '../seleccion-editar/seleccion-editar.component';

@Component({
  selector: 'app-seleccion',
  imports: [
    ReferenciasMaterialModule,
    NgxDatatableModule,
    FormsModule
  ],
  templateUrl: './seleccion.component.html',
  styleUrl: './seleccion.component.css'
})
export class SeleccionComponent implements OnInit {

  public columnas = [
    { name: "Nombre Seleccción", prop: "nombre" },
    { name: "Entidad Rectora de Fútbol", prop: "entidad" }
  ];
  public modoColumna = ColumnMode;

  public selecciones: Seleccion[] = [];

  public textoBusqueda: string = "";

  constructor(private servicioSeleccion: SeleccionService,
    private servicioDialogo: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.listar();
  }

  public listar() {
    this.servicioSeleccion.listar().subscribe({
      next: response => {
        this.selecciones = response;
      },
      error: error => {
        window.alert(error.message);
      }
    });
  }

  public buscar() {
    if (this.textoBusqueda.length > 0) {
      this.servicioSeleccion.buscar(this.textoBusqueda).subscribe({
        next: response => {
          this.selecciones = response;
        },
        error: error => {
          window.alert(error.message);
        }
      });
    }
    else {
      this.listar();
    }
  }

  public agregar() {
    const ventanaDialogo = this.servicioDialogo.open(SeleccionEditarComponent,
      {
        width: "500px",
        height: "300px",
        data: {
          encabezado: "Agregando nueva Selección de Fútbol",
          seleccion: {
            id: 0,
            nombre: "",
            entidad: ""
          }
        },
        disableClose: true
      }
    );

    ventanaDialogo.afterClosed().subscribe({
      next: datos => {
        if (datos) {
          this.servicioSeleccion.agregar(datos.seleccion).subscribe({
            next: Response => {
              this.textoBusqueda = Response.nombre;
              this.buscar();
            },
            error: error => {
              window.alert(error.message);
            }
          });
        }
      },
      error: error => {
        window.alert(error.message);
      }
    });
  }


}
