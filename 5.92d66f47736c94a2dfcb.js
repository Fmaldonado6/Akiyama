(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{"8/RF":function(t,n,e){"use strict";e.r(n),e.d(n,"FavoritesModule",function(){return x});var i=e("PCNd"),c=e("ofXK"),s=e("sJtc"),a=e("Sy1n"),o=e("kkd5"),r=e("fXoL"),l=e("iirt"),b=e("0IaG"),d=e("qFsG"),g=e("NFeN"),p=e("cfTy"),u=e("Xa2L");let m=(()=>{class t{constructor(){this.message="Empty"}ngOnInit(){}}return t.\u0275fac=function(n){return new(n||t)},t.\u0275cmp=r.Hb({type:t,selectors:[["empty"]],inputs:{message:"message"},decls:5,vars:1,consts:[[1,"content"]],template:function(t,n){1&t&&(r.Tb(0,"div",0),r.Tb(1,"mat-icon"),r.Bc(2,"help_outline"),r.Sb(),r.Tb(3,"span"),r.Bc(4),r.Sb(),r.Sb()),2&t&&(r.Cb(4),r.Cc(n.message))},directives:[g.a],styles:[".content[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-content:center;align-items:center;color:var(--secondary-text-color)}.content[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-bottom:25px;transform:scale(2.25)}.content[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{text-align:center;font-size:16px}"]}),t})();function h(t,n){if(1&t){const t=r.Ub();r.Tb(0,"anime-item-list",9),r.ac("click",function(){r.sc(t);const e=n.$implicit;return r.ec(2).changeToDetail(e)}),r.Sb()}2&t&&r.jc("anime",n.$implicit)}function f(t,n){if(1&t&&(r.Tb(0,"div",7),r.zc(1,h,1,1,"anime-item-list",8),r.Sb()),2&t){const t=r.ec();r.Cb(1),r.jc("ngForOf",t.favorites)}}function C(t,n){1&t&&(r.Tb(0,"div",10),r.Ob(1,"mat-spinner",11),r.Sb()),2&t&&(r.Cb(1),r.jc("diameter",40))}function v(t,n){1&t&&(r.Tb(0,"div",12),r.Ob(1,"empty",13),r.Sb())}let O=(()=>{class t{constructor(t,n){this.favoritesService=t,this.dialog=n,this.Status=s.b,this.currentStatus=s.b.empty,this.favorites=[]}ngOnDestroy(){this.subscription||this.subscription.unsubscribe()}ngOnInit(){this.subscription=this.favoritesService.favorites.asObservable().subscribe(t=>{this.favorites=t,this.currentStatus=0!=this.favorites.length?s.b.loaded:s.b.empty})}changeToDetail(t){this.dialog.open(o.a,{data:{anime:t},panelClass:a.b.enabled?"modal-dark":"modal"})}}return t.\u0275fac=function(n){return new(n||t)(r.Nb(l.a),r.Nb(b.b))},t.\u0275cmp=r.Hb({type:t,selectors:[["app-favorites"]],decls:11,vars:4,consts:[[1,"container",3,"ngSwitch"],[1,"search"],["placeholder","Search favorites","matInput",""],["input",""],["class","search-results",4,"ngSwitchCase"],["class","loading",4,"ngSwitchCase"],["class","empty",4,"ngSwitchCase"],[1,"search-results"],[3,"anime","click",4,"ngFor","ngForOf"],[3,"anime","click"],[1,"loading"],[3,"diameter"],[1,"empty"],["message","You haven't added any favorites"]],template:function(t,n){1&t&&(r.Tb(0,"div",0),r.Tb(1,"h1"),r.Bc(2,"Favorites"),r.Sb(),r.Tb(3,"div",1),r.Ob(4,"input",2,3),r.Tb(6,"mat-icon"),r.Bc(7,"search"),r.Sb(),r.Sb(),r.zc(8,f,2,1,"div",4),r.zc(9,C,2,1,"div",5),r.zc(10,v,2,0,"div",6),r.Sb()),2&t&&(r.jc("ngSwitch",n.currentStatus),r.Cb(8),r.jc("ngSwitchCase",n.Status.loaded),r.Cb(1),r.jc("ngSwitchCase",n.Status.loading),r.Cb(1),r.jc("ngSwitchCase",n.Status.empty))},directives:[c.m,d.b,g.a,c.n,c.j,p.a,u.b,m],styles:[".search[_ngcontent-%COMP%]{background-color:var(--background);border-radius:13px;height:50px;width:30%;box-shadow:0 6px 11px 0 rgba(0,0,0,.34);display:flex;align-items:center}.search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{background-color:initial;border:none;font-size:16px;width:100%;padding:0 15px;outline:none}.search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%], .search[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{color:var(--secondary-text-color)}.search[_ngcontent-%COMP%]   mat-icon[_ngcontent-%COMP%]{margin-right:20px}@media (max-width:1000px){.search[_ngcontent-%COMP%]{width:100%}}.empty[_ngcontent-%COMP%], .loading[_ngcontent-%COMP%]{width:100%;height:50vh;display:flex;align-items:center;justify-content:center}.search-results[_ngcontent-%COMP%]{margin-top:20px;display:flex;flex-wrap:wrap;justify-content:space-between}.search-results[_ngcontent-%COMP%]   anime-item-list[_ngcontent-%COMP%]{width:45%}@media (max-width:1000px){.search-results[_ngcontent-%COMP%]   anime-item-list[_ngcontent-%COMP%]{width:100%}}"]}),t})();var w=e("tyNb");const S=[{path:"",component:O}];let y=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=r.Lb({type:t}),t.\u0275inj=r.Kb({imports:[[w.g.forChild(S)],w.g]}),t})(),x=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=r.Lb({type:t}),t.\u0275inj=r.Kb({imports:[[c.c,y,i.a]]}),t})()}}]);