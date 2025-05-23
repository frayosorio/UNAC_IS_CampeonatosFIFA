import { Component, Inject } from '@angular/core';
import { ReferenciasMaterialModule } from '../../../shared/modulos/referencias-material.module';
import { FormsModule } from '@angular/forms';
import { Seleccion } from '../../../shared/entidades/seleccion';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

export interface DatosEdicionSeleccion {
  encabezado: string,
  seleccion: Seleccion
}

@Component({
  selector: 'app-seleccion-editar',
  imports: [
    ReferenciasMaterialModule,
    FormsModule
  ],
  templateUrl: './seleccion-editar.component.html',
  styleUrl: './seleccion-editar.component.css'
})
export class SeleccionEditarComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public datos: DatosEdicionSeleccion,
    private dialogRef: MatDialogRef<SeleccionEditarComponent>
  ) {

  }

  public cerrar() {
    this.dialogRef.close();
  }

}
