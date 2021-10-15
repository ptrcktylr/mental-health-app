export class Entry {

    constructor(
        public entryId:number,
        public author:any,
        public title:String,
        public body:String,
        public tags:String,
        public sentiment:number,
        public isPublic:boolean,
        public datePosted:String
    ){}
}
