export class Note {
  constructor(
    public id: number,
    public version: number,
    public title: string,
    public content: string,
    public createdDate: Date,
    public modifiedDate: Date
  ) {}
}
