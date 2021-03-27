// import { TestBed , async } from '@angular/core/testing';
// import { RouterTestingModule } from '@angular/router/testing';
// import { AppComponent } from './app.component';
//
// import { HttpClientModule } from '@angular/common/http';
// // import { AppService } from './app.service';
// import { of } from 'rxjs';
//
// // class FakeAppService extends AppService {
// //   getWelcomeMessage() {
// //     return of({
// //       content: 'Test content'
// //     });
// //   }
// // }
// //
// // describe('AppComponent', () => {
// //   beforeEach(async(() => {
// //     TestBed.configureTestingModule({
// //       declarations: [
// //         AppComponent
// //       ],
// //       imports: [
// //         HttpClientModule,
// //         RouterTestingModule
// //       ]
// //     }).overrideComponent(AppComponent, {
// //       set: {
// //         providers: [
// //           { provide: AppService, useClass: FakeAppService}
// //         ]
// //       }
// //     }).compileComponents();
// //   }));
//
// // describe('AppComponent', () => {
// //   beforeEach(async () => {
// //     await TestBed.configureTestingModule({
// //       imports: [
// //         RouterTestingModule,
// //         HttpClientModule
// //       ],
// //       declarations: [
// //         AppComponent
// //       ],
// //     }).compileComponents();
// //   });
//
//   it('should create the app', () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     const app = fixture.componentInstance;
//     expect(app).toBeTruthy();
//   });
//
//   it(`should have as title 'ui'`, () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     const app = fixture.componentInstance;
//     expect(app.title).toEqual('ui');
//   });
//
//   it('should render title', () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     fixture.detectChanges();
//     const compiled = fixture.nativeElement;
//     expect(compiled.querySelector('.content span').textContent).toContain('ui app is running!');
//   });
// });
