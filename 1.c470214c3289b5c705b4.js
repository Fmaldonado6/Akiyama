(window.webpackJsonp=window.webpackJsonp||[]).push([[1],{DFav:function(t,e,n){"use strict";n.d(e,"a",function(){return h});var i=n("JIr8"),o=n("lJxs"),r=n("2Vo4");class s{constructor(t){this.originalError=t}}class c extends s{}class a extends s{}var d=n("AytR"),l=n("z6cu"),p=n("fXoL"),b=n("tk/3");let u=(()=>{class t{constructor(t){this.http=t,this.url=d.a.base_url}handleError(t){return 400===t.status?Object(l.a)(new c(t)):404===t.status?Object(l.a)(new a(t)):Object(l.a)(new s(t))}}return t.\u0275fac=function(e){return new(e||t)(p.Xb(b.a))},t.\u0275prov=p.Jb({token:t,factory:t.\u0275fac,providedIn:"root"}),t})(),h=(()=>{class t extends u{constructor(){super(...arguments),this.animeObserver=new r.a([]),this.animes=[],this.ovas=[],this.specials=[],this.movies=[],this.episodes=[]}setAnimes(t){this.animes=t,this.animeObserver.next(t)}setOvas(t){this.ovas=t}setSpecials(t){this.specials=t}setMovies(t){this.movies=t}setEpisodes(t){this.episodes=t}fetchEpisodes(){return this.http.get(`${this.url}/animes/episodes`).pipe(Object(i.a)(this.handleError))}fetchAnimes(){return this.http.get(`${this.url}/animes/latest`).pipe(Object(i.a)(this.handleError),Object(o.a)(t=>(this.setAnimes(t),t)))}fetchSpecials(){return this.http.get(`${this.url}/animes/specials`).pipe(Object(i.a)(this.handleError))}fetchOvas(){return this.http.get(`${this.url}/animes/ovas`).pipe(Object(i.a)(this.handleError))}fetchMovies(){return this.http.get(`${this.url}/animes/movies`).pipe(Object(i.a)(this.handleError))}getAnimeInfo(t,e){return this.http.get(`${this.url}/animes/${t}/${e}`).pipe(Object(i.a)(this.handleError))}getEpisoderServers(t){return this.http.get(`${this.url}/animes/episodes/${t}`).pipe(Object(i.a)(this.handleError))}search(t){return this.http.get(`${this.url}/animes/search/${t}`).pipe(Object(i.a)(this.handleError))}}return t.\u0275fac=function(e){return g(e||t)},t.\u0275prov=p.Jb({token:t,factory:t.\u0275fac,providedIn:"root"}),t})();const g=p.Vb(h)},KIY9:function(t,e,n){"use strict";n.d(e,"a",function(){return f});var i=n("sJtc"),o=n("fXoL"),r=n("2ChS"),s=n("DFav"),c=n("tyNb"),a=n("ofXK"),d=n("FKr1"),l=n("Xa2L"),p=n("UyyX");function b(t,e){if(1&t){const t=o.Ub();o.Tb(0,"span",5),o.ac("click",function(){o.sc(t);const n=e.$implicit;return o.ec(2).openServer(n)}),o.Bc(1),o.Sb()}if(2&t){const t=e.$implicit;o.Cb(1),o.Cc(t.title)}}function u(t,e){if(1&t&&(o.Rb(0),o.Tb(1,"div",3),o.zc(2,b,2,1,"span",4),o.Sb(),o.Qb()),2&t){const t=o.ec();o.Cb(2),o.jc("ngForOf",t.servers)}}function h(t,e){1&t&&(o.Tb(0,"div",6),o.Ob(1,"mat-spinner",7),o.Sb()),2&t&&(o.Cb(1),o.jc("diameter",40))}function g(t,e){if(1&t){const t=o.Ub();o.Tb(0,"div",6),o.Tb(1,"error",8),o.ac("buttonClicked",function(){return o.sc(t),o.ec().getServers()}),o.Sb(),o.Sb()}}let f=(()=>{class t{constructor(t,e,n){this.animeService=t,this.router=e,this.sheetData=n,this.Status=i.b,this.currentStatus=i.b.loading,this.servers=[],this.serverSelected=new o.o}ngOnInit(){this.episode=this.sheetData.episode,this.episode.servers?(this.servers=this.episode.servers,this.currentStatus=i.b.loaded):this.getServers()}openServer(t){const e={queryParams:{url:t.code}};this.serverSelected.emit(),this.router.navigate(["/watch"],e)}getServers(){this.currentStatus=i.b.loading,this.animeService.getEpisoderServers(this.episode.id).subscribe(t=>{this.servers=t,this.currentStatus=i.b.loaded},()=>{this.currentStatus=i.b.error})}}return t.\u0275fac=function(e){return new(e||t)(o.Nb(s.a),o.Nb(c.c),o.Nb(r.a))},t.\u0275cmp=o.Hb({type:t,selectors:[["app-servers"]],outputs:{serverSelected:"serverSelected"},decls:6,vars:4,consts:[[1,"bottom-sheet",3,"ngSwitch"],[4,"ngSwitchCase"],["class","info",4,"ngSwitchCase"],[1,"servers-container"],["mat-ripple","",3,"click",4,"ngFor","ngForOf"],["mat-ripple","",3,"click"],[1,"info"],[3,"diameter"],[3,"buttonClicked"]],template:function(t,e){1&t&&(o.Tb(0,"div",0),o.Tb(1,"h3"),o.Bc(2,"Select an option"),o.Sb(),o.zc(3,u,3,1,"ng-container",1),o.zc(4,h,2,1,"div",2),o.zc(5,g,2,0,"div",2),o.Sb()),2&t&&(o.jc("ngSwitch",e.currentStatus),o.Cb(3),o.jc("ngSwitchCase",e.Status.loaded),o.Cb(1),o.jc("ngSwitchCase",e.Status.loading),o.Cb(1),o.jc("ngSwitchCase",e.Status.error))},directives:[a.m,a.n,a.j,d.q,l.b,p.a],styles:[".bottom-sheet[_ngcontent-%COMP%]{min-height:200px;padding:10px}.servers-container[_ngcontent-%COMP%]{display:flex;flex-wrap:wrap;justify-content:space-between}.servers-container[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{width:40%;border:1px solid var(--primary);padding:7px 10px;cursor:pointer;border-radius:20px;text-align:center;font-size:16px;margin-bottom:15px}.info[_ngcontent-%COMP%]{display:flex;justify-content:center;align-items:center;height:150px;width:100%}"]}),t})()},UyyX:function(t,e,n){"use strict";n.d(e,"a",function(){return s});var i=n("fXoL"),o=n("NFeN"),r=n("FKr1");let s=(()=>{class t{constructor(){this.buttonClicked=new i.o}ngOnInit(){}onClick(){this.buttonClicked.emit()}}return t.\u0275fac=function(e){return new(e||t)},t.\u0275cmp=i.Hb({type:t,selectors:[["error"]],outputs:{buttonClicked:"buttonClicked"},decls:5,vars:0,consts:[[1,"content"],["matRipple","",3,"click"]],template:function(t,e){1&t&&(i.Tb(0,"div",0),i.Tb(1,"mat-icon"),i.Bc(2,"error_outline"),i.Sb(),i.Tb(3,"span",1),i.ac("click",function(){return e.onClick()}),i.Bc(4,"Retry"),i.Sb(),i.Sb())},directives:[o.a,r.q],styles:[".content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:center;align-items:center}.content[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{color:var(--secondary-text-color);margin-bottom:25px;transform:scale(2.25)}.content[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{text-align:center;font-size:16px;padding:5px 15px;border-radius:20px;width:50px;border:2px solid var(--primary)}"]}),t})()},cfTy:function(t,e,n){"use strict";n.d(e,"a",function(){return s});var i=n("fXoL"),o=n("FKr1"),r=n("NFeN");let s=(()=>{class t{constructor(){}ngOnInit(){if(!this.anime)throw new Error("Anime is required")}}return t.\u0275fac=function(e){return new(e||t)},t.\u0275cmp=i.Hb({type:t,selectors:[["anime-item-list"]],inputs:{anime:"anime"},decls:6,vars:2,consts:[["matRipple","",1,"anime-item","mat-elevation-z4"],[3,"src"]],template:function(t,e){1&t&&(i.Tb(0,"div",0),i.Ob(1,"img",1),i.Tb(2,"span"),i.Bc(3),i.Sb(),i.Tb(4,"mat-icon"),i.Bc(5,"chevron_right"),i.Sb(),i.Sb()),2&t&&(i.Cb(1),i.kc("src","data:image/png;base64,",e.anime.poster,"",i.uc),i.Cb(2),i.Cc(e.anime.title))},directives:[o.q,r.a],styles:[".anime-item[_ngcontent-%COMP%]{display:flex;padding:10px;border-radius:20px;align-items:center;cursor:pointer;margin-bottom:10px;transition:background-color .3s ease}.anime-item[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{width:100px;margin-right:20px;height:75px;border-radius:10px;object-fit:cover}.anime-item[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-left:auto}.anime-item[_ngcontent-%COMP%]:hover{background-color:var(--background)}"]}),t})()},iirt:function(t,e,n){"use strict";n.d(e,"a",function(){return r});var i=n("2Vo4"),o=n("fXoL");let r=(()=>{class t{constructor(){this.FAVORITES_KEY="akiyama_favorites",this._favorites=[],this.favorites=new i.a([]),this.getFavorites()}addFavorite(t){this._favorites.push(t),this.favorites.next(this._favorites),localStorage.setItem(this.FAVORITES_KEY,JSON.stringify(this._favorites))}isFavorite(t){for(let e of this._favorites)if(e.id==t.id)return!0;return!1}removeFavorite(t){this._favorites=this._favorites.filter(e=>e.id!=t.id),this.favorites.next(this._favorites),localStorage.setItem(this.FAVORITES_KEY,JSON.stringify(this._favorites))}getFavorites(){const t=localStorage.getItem(this.FAVORITES_KEY);if(!t)return;const e=JSON.parse(t);this._favorites=e,this.favorites.next(this._favorites)}}return t.\u0275fac=function(e){return new(e||t)},t.\u0275prov=o.Jb({token:t,factory:t.\u0275fac,providedIn:"root"}),t})()},kkd5:function(t,e,n){"use strict";n.d(e,"a",function(){return w});var i=n("KIY9"),o=n("0IaG"),r=n("sJtc"),s=n("Sy1n"),c=n("fXoL"),a=n("2ChS"),d=n("iirt"),l=n("ofXK"),p=n("bTqV"),b=n("NFeN"),u=n("FKr1");let h=(()=>{class t{constructor(){this.icon="",this.label=""}ngOnInit(){if(!this.icon||!this.label)throw new Error("Missing attributes")}}return t.\u0275fac=function(e){return new(e||t)},t.\u0275cmp=c.Hb({type:t,selectors:[["icon-button"]],inputs:{icon:"icon",label:"label"},decls:5,vars:2,consts:[["mat-ripple","",1,"button-container"]],template:function(t,e){1&t&&(c.Tb(0,"div",0),c.Tb(1,"mat-icon"),c.Bc(2),c.Sb(),c.Tb(3,"span"),c.Bc(4),c.Sb(),c.Sb()),2&t&&(c.Cb(2),c.Cc(e.icon),c.Cb(2),c.Cc(e.label))},directives:[u.q,b.a],styles:[".button-container[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:center;align-items:center;border-radius:10px;display:inline-flex;padding:5px 10px;cursor:pointer;transition:background-color .3s ease}.button-container[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-bottom:5px}.button-container[_ngcontent-%COMP%]:hover{background-color:var(--background)}"]}),t})();var g=n("Xa2L");function f(t,e){if(1&t&&(c.Tb(0,"div",18),c.Tb(1,"span"),c.Bc(2),c.Sb(),c.Sb()),2&t){const t=e.$implicit;c.Cb(2),c.Cc(t)}}function m(t,e){if(1&t&&(c.Tb(0,"span"),c.Bc(1),c.Sb()),2&t){const t=c.ec(2).$implicit;c.Cb(1),c.Dc("Episode ",t.episode,"")}}function v(t,e){if(1&t&&(c.Tb(0,"span"),c.Bc(1),c.Sb()),2&t){const t=c.ec(2).$implicit;c.Cb(1),c.Dc("Next episode ",t.nextEpisodeDate,"")}}function C(t,e){if(1&t){const t=c.Ub();c.Tb(0,"div",20),c.ac("click",function(){c.sc(t);const e=c.ec().$implicit;return c.ec(2).openServersBottomSheet(e)}),c.zc(1,m,2,1,"span",21),c.zc(2,v,2,1,"span",21),c.Sb()}if(2&t){const t=c.ec().$implicit;c.Cb(1),c.jc("ngIf",!t.nextEpisodeDate),c.Cb(1),c.jc("ngIf",t.nextEpisodeDate)}}function S(t,e){if(1&t&&(c.Rb(0),c.zc(1,C,3,2,"div",19),c.Qb()),2&t){const t=e.$implicit,n=e.index;c.Cb(1),c.jc("ngIf",!(!t.nextEpisodeDate||0!=n)||0!=n)}}const x=function(t){return{favorite:t}},O=function(t){return{expanded:t}};function _(t,e){if(1&t){const t=c.Ub();c.Rb(0),c.Tb(1,"div",4),c.Ob(2,"img",5),c.Sb(),c.Tb(3,"div",6),c.Tb(4,"div",7),c.Ob(5,"img",5),c.Tb(6,"div",8),c.Tb(7,"h2"),c.Bc(8),c.Sb(),c.Tb(9,"span"),c.Bc(10),c.Sb(),c.Sb(),c.Sb(),c.Tb(11,"div",9),c.Tb(12,"icon-button",10),c.ac("click",function(){return c.sc(t),c.ec().favoritesToggle()}),c.Sb(),c.Sb(),c.Tb(13,"div",11),c.Tb(14,"p",12),c.Bc(15),c.Sb(),c.Tb(16,"button",13),c.ac("click",function(){return c.sc(t),c.ec().expand()}),c.Bc(17,"More"),c.Sb(),c.Sb(),c.Tb(18,"div",14),c.zc(19,f,3,1,"div",15),c.Sb(),c.Tb(20,"div",16),c.Tb(21,"h3"),c.Bc(22,"Episodes"),c.Sb(),c.zc(23,S,2,1,"ng-container",17),c.Sb(),c.Sb(),c.Qb()}if(2&t){const t=c.ec();c.Cb(2),c.kc("src","data:image/png;base64,",t.anime.poster,"",c.uc),c.Cb(3),c.kc("src","data:image/png;base64,",t.anime.poster,"",c.uc),c.Cb(3),c.Cc(t.anime.title),c.Cb(2),c.Cc(t.anime.debut),c.Cb(2),c.jc("ngClass",c.mc(11,x,t.isFavorite))("label",t.isFavorite?t.label.favorite:t.label.notFavorite)("icon",t.isFavorite?t.icons.favorite:t.icons.notFavorite),c.Cb(2),c.jc("ngClass",c.mc(13,O,t.expandedSynopsis)),c.Cb(1),c.Dc(" ",t.anime.synopsis," "),c.Cb(4),c.jc("ngForOf",t.anime.genres),c.Cb(4),c.jc("ngForOf",t.anime.episodes)}}function y(t,e){1&t&&(c.Tb(0,"div",22),c.Ob(1,"mat-spinner",23),c.Sb()),2&t&&(c.Cb(1),c.jc("diameter",40))}let w=(()=>{class t{constructor(t,e,n,i){this.dialogRef=t,this.bottomSheet=e,this.favoritesService=n,this.modalData=i,this.Status=r.b,this.currentStatus=r.b.loading,this.isFavorite=!1,this.expandedSynopsis=!1,this.label={favorite:"Remove from favorites",notFavorite:"Add to favorites"},this.icons={favorite:"favorite",notFavorite:"favorite_border"}}ngOnInit(){this.anime=this.modalData.anime,this.currentStatus=r.b.loaded,this.isFavorite=this.favoritesService.isFavorite(this.anime)}openServersBottomSheet(t){if(t.nextEpisodeDate)return;const e=this.bottomSheet.open(i.a,{panelClass:s.b.enabled?"bottomSheet-dark":"",data:{episode:t}});e.instance.serverSelected.subscribe(t=>{e.dismiss(),this.close()})}favoritesToggle(){this.isFavorite?this.favoritesService.removeFavorite(this.anime):this.favoritesService.addFavorite(this.anime),this.isFavorite=this.favoritesService.isFavorite(this.anime)}expand(){this.expandedSynopsis=!this.expandedSynopsis}close(){this.dialogRef.close()}}return t.\u0275fac=function(e){return new(e||t)(c.Nb(o.d),c.Nb(a.b),c.Nb(d.a),c.Nb(o.a))},t.\u0275cmp=c.Hb({type:t,selectors:[["app-anime-detail"]],decls:6,vars:3,consts:[[1,"dialog",3,"ngSwitch"],["mat-icon-button","",1,"close",3,"click"],[4,"ngSwitchCase"],["class","loading",4,"ngSwitchCase"],[1,"background"],[3,"src"],[1,"content"],[1,"header"],[1,"title"],[1,"buttons"],[3,"ngClass","label","icon","click"],[1,"synopsis-container"],[3,"ngClass"],["mat-button","","color","primary",3,"click"],[1,"genres-container"],["class","genre",4,"ngFor","ngForOf"],[1,"episodes-container"],[4,"ngFor","ngForOf"],[1,"genre"],["class","episode","mat-ripple","",3,"click",4,"ngIf"],["mat-ripple","",1,"episode",3,"click"],[4,"ngIf"],[1,"loading"],[3,"diameter"]],template:function(t,e){1&t&&(c.Tb(0,"div",0),c.Tb(1,"button",1),c.ac("click",function(){return e.close()}),c.Tb(2,"mat-icon"),c.Bc(3,"close"),c.Sb(),c.Sb(),c.zc(4,_,24,15,"ng-container",2),c.zc(5,y,2,1,"div",3),c.Sb()),2&t&&(c.jc("ngSwitch",e.currentStatus),c.Cb(4),c.jc("ngSwitchCase",e.Status.loaded),c.Cb(1),c.jc("ngSwitchCase",e.Status.loading))},directives:[l.m,p.a,b.a,l.n,h,l.i,l.j,l.k,u.q,g.b],styles:['.dialog[_ngcontent-%COMP%]{position:relative}.close[_ngcontent-%COMP%], .dialog[_ngcontent-%COMP%]{color:var(--text-color)}.close[_ngcontent-%COMP%]{position:absolute;right:10px;top:10px;z-index:10}.content[_ngcontent-%COMP%]{position:relative;padding:24px;overflow-y:auto}.background[_ngcontent-%COMP%]{position:absolute;height:300px;top:0;left:0;width:100%;z-index:0}.background[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{width:100%;height:100%;object-fit:cover}.background[_ngcontent-%COMP%]:before{content:" ";position:absolute;left:0;top:0;width:100%;height:100%;z-index:0;background:hsla(0,0%,100%,0);background:linear-gradient(180deg,var(--secondary-background-alpha) 0,var(--secondary-background) 100%)}.favorite[_ngcontent-%COMP%]{color:var(--primary)}.header[_ngcontent-%COMP%]{display:flex;padding:15px 0;overflow:hidden;align-items:center;justify-content:center}.header[_ngcontent-%COMP%]   img[_ngcontent-%COMP%]{width:150px;object-fit:cover;height:210px;border-radius:10px;box-shadow:0 6px 11px 0 rgba(0,0,0,.34)}.header[_ngcontent-%COMP%]   .title[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:center;color:var(--text-color);margin:20px}.header[_ngcontent-%COMP%]   .title[_ngcontent-%COMP%]   h2[_ngcontent-%COMP%]{margin-bottom:7px;overflow:hidden;max-lines:2;text-overflow:ellipsis}.header[_ngcontent-%COMP%]   .title[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{color:var(--secondary-text-color);font-size:16px}.buttons[_ngcontent-%COMP%]{margin:20px 0;display:flex;align-items:center;justify-content:center}.genres-container[_ngcontent-%COMP%]{margin-bottom:20px;display:flex;overflow-x:auto}.genre[_ngcontent-%COMP%]{border:1px solid var(--primary);padding:3px 10px;border-radius:20px;color:var(--primary);margin-right:10px;min-width:-webkit-fit-content;min-width:-moz-fit-content;min-width:fit-content}.synopsis-container[_ngcontent-%COMP%]{margin-bottom:20px;font-size:15px;display:flex;flex-direction:column}.synopsis-container[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{height:43px;overflow:hidden}.synopsis-container[_ngcontent-%COMP%]   .expanded[_ngcontent-%COMP%]{height:-webkit-fit-content;height:-moz-fit-content;height:fit-content}.synopsis-container[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{margin-left:auto}.loading[_ngcontent-%COMP%]{width:100%;height:90vh;display:flex;align-items:center;justify-content:center}.episode[_ngcontent-%COMP%]{padding:15px;cursor:pointer;font-size:15px;transition:background-color .3s ease}.episode[_ngcontent-%COMP%]:hover{background-color:var(--secondary-background-lighter)}@media (max-width:600px){.header[_ngcontent-%COMP%]{flex-direction:column}.header[_ngcontent-%COMP%]   .title[_ngcontent-%COMP%]{margin-top:40px;margin-bottom:0}}']}),t})()},sJtc:function(t,e,n){"use strict";n.d(e,"a",function(){return i}),n.d(e,"c",function(){return o}),n.d(e,"b",function(){return r});class i{constructor(t=o.Anime,e=[],n=[],i=r.loading){this.type=t,this.animeList=e,this.episodeList=n,this.currentStatus=i}}var o=function(t){return t[t.Anime=0]="Anime",t[t.Episode=1]="Episode",t}({}),r=function(t){return t[t.loading=0]="loading",t[t.loaded=1]="loaded",t[t.error=2]="error",t[t.empty=3]="empty",t}({})}}]);