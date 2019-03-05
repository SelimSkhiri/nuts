import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NutsWorkspaceService} from '../../../pages/nuts-workspace/nuts-workspace.service';
import {NutsWorkspace} from '../../../pages/nuts-workspace/nuts-workspace';

@Component({
  selector: 'ngx-table-switcher',
  template: `
    <ui-switch
      [checked]="value === 'true'"
      (change)="turnOnOff($event)">
    </ui-switch>
  `,
  styleUrls: ['./workspace-enable-switcher.component.scss'],
})
export class WorkspaceEnableSwitcherComponent implements OnInit {

  @Input() value: string;
  @Input() rowData: NutsWorkspace;

  @Output() save: EventEmitter<any> = new EventEmitter();

  constructor(private workspaceService: NutsWorkspaceService) {
  }

  ngOnInit() {
  }

  turnOnOff(event) {
    this.workspaceService.switchEnabled(this.rowData.name, event);
  }
}
