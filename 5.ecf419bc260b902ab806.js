(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{"8/RF":function(t,e,n){"use strict";n.r(e),n.d(e,"FavoritesModule",function(){return M});var i=n("PCNd"),s=n("ofXK"),c=n("sJtc"),a=n("kkd5"),o=n("fXoL"),r=n("iirt"),l=n("0IaG"),d=n("+wKw"),u=n("qFsG"),b=n("NFeN"),p=n("cfTy"),h=n("Xa2L");let g=(()=>{class t{constructor(){this.message="Empty"}ngOnInit(){}}return t.\u0275fac=function(e){return new(e||t)},t.\u0275cmp=o.Hb({type:t,selectors:[["empty"]],inputs:{message:"message"},decls:5,vars:1,consts:[[1,"content"]],template:function(t,e){1&t&&(o.Tb(0,"div",0),o.Tb(1,"mat-icon"),o.Bc(2,"help_outline"),o.Sb(),o.Tb(3,"span"),o.Bc(4),o.Sb(),o.Sb()),2&t&&(o.Cb(4),o.Cc(e.message))},directives:[b.a],styles:[".content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:center;align-items:center;color:var(--secondary-text-color)}.content[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-bottom:25px;transform:scale(2.25)}.content[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{text-align:center;font-size:16px}"]}),t})();function m(t,e){if(1&t){const t=o.Ub();o.Tb(0,"anime-item-list",9),o.ac("click",function(){o.sc(t);const n=e.$implicit;return o.ec(2).changeToDetail(n)}),o.Sb()}2&t&&o.jc("anime",e.$implicit)}function f(t,e){if(1&t&&(o.Tb(0,"div",7),o.zc(1,m,1,1,"anime-item-list",8),o.Sb()),2&t){const t=o.ec();o.Cb(1),o.jc("ngForOf",t.filteredFavorites)}}function v(t,e){1&t&&(o.Tb(0,"div",10),o.Ob(1,"mat-spinner",11),o.Sb()),2&t&&(o.Cb(1),o.jc("diameter",40))}function C(t,e){1&t&&(o.Tb(0,"div",12),o.Ob(1,"empty",13),o.Sb())}let w=(()=>{class t{constructor(t,e,n){this.favoritesService=t,this.dialog=e,this.darkModeService=n,this.Status=c.b,this.currentStatus=c.b.empty,this.favorites=[],this.filteredFavorites=[]}ngOnDestroy(){this.subscription||this.subscription.unsubscribe()}ngOnInit(){this.subscription=this.favoritesService.favorites.asObservable().subscribe(t=>{this.favorites=t,this.filteredFavorites=t,this.currentStatus=0!=this.favorites.length?c.b.loaded:c.b.empty})}filter(t){this.filteredFavorites=""!=t&&t?this.favorites.filter(e=>e.title.toLowerCase().includes(t.toLowerCase())):this.favorites}changeToDetail(t){this.dialog.open(a.a,{data:{anime:t},panelClass:this.darkModeService.enabled.value?"modal-dark":"modal"})}}return t.\u0275fac=function(e){return new(e||t)(o.Nb(r.a),o.Nb(l.b),o.Nb(d.a))},t.\u0275cmp=o.Hb({type:t,selectors:[["app-favorites"]],decls:11,vars:4,consts:[[1,"container",3,"ngSwitch"],[1,"search"],["placeholder","Search favorites","matInput","",3,"input"],["input",""],["class","search-results",4,"ngSwitchCase"],["class","loading",4,"ngSwitchCase"],["class","empty",4,"ngSwitchCase"],[1,"search-results"],[3,"anime","click",4,"ngFor","ngForOf"],[3,"anime","click"],[1,"loading"],[3,"diameter"],[1,"empty"],["message","You haven't added any favorites"]],template:function(t,e){if(1&t){const t=o.Ub();o.Tb(0,"div",0),o.Tb(1,"h1"),o.Bc(2,"Favorites"),o.Sb(),o.Tb(3,"div",1),o.Tb(4,"input",2,3),o.ac("input",function(){o.sc(t);const n=o.pc(5);return e.filter(n.value)}),o.Sb(),o.Tb(6,"mat-icon"),o.Bc(7,"search"),o.Sb(),o.Sb(),o.zc(8,f,2,1,"div",4),o.zc(9,v,2,1,"div",5),o.zc(10,C,2,0,"div",6),o.Sb()}2&t&&(o.jc("ngSwitch",e.currentStatus),o.Cb(8),o.jc("ngSwitchCase",e.Status.loaded),o.Cb(1),o.jc("ngSwitchCase",e.Status.loading),o.Cb(1),o.jc("ngSwitchCase",e.Status.empty))},directives:[s.m,u.b,b.a,s.n,s.j,p.a,h.b,g],styles:[".search[_ngcontent-%COMP%]{background-color:var(--background);border-radius:13px;height:50px;width:30%;box-shadow:0 6px 11px 0 rgba(0,0,0,.34);display:flex;align-items:center}.search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{background-color:initial;border:none;font-size:16px;width:100%;padding:0 15px;outline:none}.search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%], .search[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{color:var(--secondary-text-color)}.search[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-right:20px}@media (max-width:1000px){.search[_ngcontent-%COMP%]{width:100%}}.empty[_ngcontent-%COMP%], .loading[_ngcontent-%COMP%]{width:100%;height:50vh;display:flex;align-items:center;justify-content:center}.search-results[_ngcontent-%COMP%]{margin-top:20px;display:flex;flex-wrap:wrap;justify-content:space-between}.search-results[_ngcontent-%COMP%]   anime-item-list[_ngcontent-%COMP%]{width:45%}@media (max-width:1000px){.search-results[_ngcontent-%COMP%]   anime-item-list[_ngcontent-%COMP%]{width:100%}}"]}),t})();var S=n("tyNb");const O=[{path:"",component:w}];let y=(()=>{class t{}return t.\u0275fac=function(e){return new(e||t)},t.\u0275mod=o.Lb({type:t}),t.\u0275inj=o.Kb({imports:[[S.g.forChild(O)],S.g]}),t})(),M=(()=>{class t{}return t.\u0275fac=function(e){return new(e||t)},t.\u0275mod=o.Lb({type:t}),t.\u0275inj=o.Kb({imports:[[s.c,y,i.a]]}),t})()}}]);