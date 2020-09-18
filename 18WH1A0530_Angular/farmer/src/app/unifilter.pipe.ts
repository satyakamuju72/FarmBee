import { Pipe, PipeTransform } from '@angular/core';
declare var jQery: any;
@Pipe({
  name: 'unifilter'
})
export class UnifilterPipe implements PipeTransform {

  transform(items: any[], args: any[]): any {
    var uniqueItems = Array.from(new Set(items));
    return uniqueItems;
  }

}
