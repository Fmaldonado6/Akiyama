import { ServerModel } from './../../../../models/api/apiContent/ServerModel';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';

interface ModalData {
  servers: ServerModel[]
}

@Component({
  selector: 'app-servers-list',
  templateUrl: './servers-list.component.html',
  styleUrls: ['./servers-list.component.css']
})
export class ServersListModal implements OnInit {

  servers: ServerModel[]

  constructor(
    private dialogRef: MatDialogRef<ServersListModal>,
    @Inject(MAT_DIALOG_DATA) public modalData: ModalData
  ) {
    this.servers = this.modalData.servers
    console.log(this.servers)
  }

  ngOnInit(): void {

  }

  serverSelected() {
    this.dialogRef.close()
  }

  back() {
    this.dialogRef.close()
  }

}
