import { Component, Inject } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Campeonato } from '../../../shared/entidades/campeonato';
import { Seleccion } from '../../../shared/entidades/seleccion';


export interface DatosEdicionCampeonato {
  encabezado: string;
  campeonato: Campeonato;
  selecciones: Seleccion[];
}

@Component({
  selector: 'app-campeonato-editar',
  imports: [
    ReferenciasMaterialModule,
    NgFor,
    FormsModule
  ],
  templateUrl: './campeonato-editar.component.html',
  styleUrl: './campeonato-editar.component.css'
})
export class CampeonatoEditarComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public datos: DatosEdicionCampeonato,
    public dialogRef: MatDialogRef<CampeonatoEditarComponent>,
  ) {

  }

  cerrar() {
    this.dialogRef.close();
  }


}
