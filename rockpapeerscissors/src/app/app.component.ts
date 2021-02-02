import { Component } from '@angular/core';
import { RockpaperscissorsService } from './rockpaperscissors.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


// @Injectable()
export class AppComponent {
    result = '';
    constructor (private rpsService: RockpaperscissorsService) {
        console.log('rspService:' + rpsService);
        rpsService.list().subscribe({
                next: result => {
                    this.result = result;
                }
        });
    }

    title = 'rockpapeerscissors';

}
