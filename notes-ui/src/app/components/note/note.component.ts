import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Note} from '../../models/note';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'ngbd-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Title: {{note.title}}</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>Content:</p>
      <p>{{note.content}}</p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})

// tslint:disable-next-line:component-class-suffix
export class NgbdModalContent {
  @Input() note: Note;

  constructor(public activeModal: NgbActiveModal) {
  }
}

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {
  @Input() note;
  notes: Note[];
  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
  ) {
  }

  ngOnInit(): void {
    this.getNotes();
  }

  // tslint:disable-next-line:typedef
  getNotes() {
    this.httpClient.get<any>('http://localhost:8080/notes/all').subscribe(
      response => {
        console.log(response);
        this.notes = response;
      }
    );
  }

  // tslint:disable-next-line:typedef
  open(note) {
    console.log(note);
    const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.note = note;
  }
}




