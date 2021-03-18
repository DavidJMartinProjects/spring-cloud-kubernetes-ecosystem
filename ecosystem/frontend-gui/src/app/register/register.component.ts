import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  dtOptions: DataTables.Settings = {
    searching: false,
    paging: false,
    info: false,
    ordering: false
  };

  ngOnInit(): void {
  }

  closeResult: String | any;

  constructor(private modalService: NgbModal) {}

  open(content: any) {
    this.modalService.open(content, {centered: true, ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
