import { Component, OnInit } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { FormsModule } from '@angular/forms';
import { ColumnMode, NgxDatatableModule, SelectionType } from '@swimlane/ngx-datatable';
import { Campeonato } from '../../../shared/entidades/campeonato';
import { CampeonatoService } from '../../../core/servicios/campeonato.service';
import { MatDialog } from '@angular/material/dialog';
import { DecidirComponent } from '../../../shared/componentes/decidir/decidir.component';
import { CampeonatoEditarComponent } from '../campeonato-editar/campeonato-editar.component';
import { SeleccionService } from '../../../core/servicios/seleccion.service';
import { Seleccion } from '../../../shared/entidades/seleccion';

@Component({
  selector: 'app-campeonato',
  imports: [
    ReferenciasMaterialModule,
    FormsModule,
    NgxDatatableModule
  ],
  templateUrl: './campeonato.component.html',
  styleUrl: './campeonato.component.css'
})
export class CampeonatoComponent implements OnInit {

  public columnas = [
    { name: "Nombre Campeonato", prop: "nombre" },
    { name: "Año", prop: "año" },
    { name: "País Organizador", prop: "pais.nombre" },
  ];
  public modoColumna = ColumnMode;
  public tipoSeleccion = SelectionType;

  public selecciones: Seleccion[] = [];
  public campeonatos: Campeonato[] = [];
  campeonatoEscogido: Campeonato | undefined;
  indiceCampeonatoEscogido: number = -1;

  public textoBusqueda: string = "";

  constructor(private servicioCampeonato: CampeonatoService,
    private servicioSeleccion: SeleccionService,
    private servicioDialogo: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.listar();
    this.listarSelecciones();
  }

  escoger(event: any) {
    if (event.type == "click") {
      this.campeonatoEscogido = event.row;
      this.indiceCampeonatoEscogido = this.campeonatos.findIndex(seleccion => seleccion == this.campeonatoEscogido);
    }
  }

  public listarSelecciones() {
    this.servicioSeleccion.listar().subscribe({
      next: response => {
        this.selecciones = response;
      },
      error: error => {
        window.alert(error.message);
      }
    });
  }

  public listar() {
    this.servicioCampeonato.listar().subscribe({
      next: response => {
        this.campeonatos = response;
      },
      error: error => {
        window.alert(error.message);
      }
    });
  }

  public buscar() {
    if (this.textoBusqueda.length > 0) {
      this.servicioCampeonato.buscar(this.textoBusqueda).subscribe({
        next: response => {
          this.campeonatos = response;
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
    const ventanaDialogo = this.servicioDialogo.open(CampeonatoEditarComponent,
      {
        width: "500px",
        height: "300px",
        data: {
          encabezado: "Agregando nuevo Campeonato Mundial",
          campeonato: {
            id: 0,
            nombre: "",
            año: 0,
            year: 0,
            pais: {
              id: 0,
              nombre: "",
              entidad: ""
            }
          },
          selecciones: this.selecciones
        },
        disableClose: true
      }
    );

    ventanaDialogo.afterClosed().subscribe({
      next: datos => {
        if (datos) {
          datos.campeonato.año = datos.campeonato.year;
          this.servicioCampeonato.agregar(datos.campeonato).subscribe({
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

  public modificar() {
    if (this.campeonatoEscogido) {
      this.campeonatoEscogido.year = this.campeonatoEscogido.año;
      const ventanaDialogo = this.servicioDialogo.open(CampeonatoEditarComponent,
        {
          width: "500px",
          height: "300px",
          data: {
            encabezado: `Editando el Campeonato [${this.campeonatoEscogido.nombre}]`,
            campeonato: this.campeonatoEscogido,
            selecciones: this.selecciones
          },
          disableClose: true
        }
      );
      ventanaDialogo.afterClosed().subscribe({
        next: datos => {
          if (datos) {
            datos.campeonato.año = datos.campeonato.year;
            this.servicioCampeonato.modificar(datos.campeonato).subscribe({
              next: response => {
                this.campeonatos[this.indiceCampeonatoEscogido] = response;
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
    else {
      window.alert("Debe escoger el Campeonato a modificar");
    }
  }

  public eliminar() {
    if (this.campeonatoEscogido) {
      const ventanaDialogo = this.servicioDialogo.open(DecidirComponent,
        {
          width: "300px",
          height: "200px",
          data: {
            encabezado: `Está seguro que desea eliminar el Campeonato [${this.campeonatoEscogido.nombre}]`,
            id: this.campeonatoEscogido.id
          },
          disableClose: true
        }
      );
      ventanaDialogo.afterClosed().subscribe({
        next: datos => {
          if (datos) {
            this.servicioCampeonato.eliminar(datos.id).subscribe({
              next: response => {
                if (response) {
                  this.listar();
                } else {
                  window.alert("No se puede eliminar el Campeonato");
                }
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
    else {
      window.alert("Debe escoger el Campeonato a eliminar");
    }
  }
}