
---

# **Document — Frontend: Angular 20+ Standalone (CRUD User)**

## 1️⃣ Introduction

This frontend application demonstrates a **complete CRUD with Angular 20+ standalone components** consuming the Spring Boot backend.

Learning objectives:

* Understand **standalone components**
* Use `provideHttpClient()` for HTTP calls
* Modern routing with `provideRouter()`
* Template-driven forms for add/edit operations

---

## 2️⃣ Project Structure

```
src/app/
│
├── main.ts              # standalone bootstrap
├── app.component.ts     # root component
├── app.routes.ts        # routing configuration
├── services/user.service.ts
└── users/
    ├── user-list.component.ts
    └── user-form.component.ts
```

---

## 3️⃣ Standalone Bootstrap

```ts
bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    provideAnimationsAsync()
  ]
});
```

**Keywords:**

* `bootstrapApplication`: starts the Angular standalone application
* `provideRouter(routes)`: activates routing
* `provideHttpClient()`: replaces `HttpClientModule` for HTTP calls
* `provideAnimationsAsync()`: supports modern animations

**Best practices:**

* Declare global providers here
* Eliminates the need for NgModules

---

## 4️⃣ HTTP Service: `UserService`

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getAll(): Observable<User[]> { return this.http.get<User[]>(this.apiUrl); }
  create(user: User): Observable<User> { return this.http.post<User>(this.apiUrl, user); }
  update(id: number, user: User): Observable<User> { return this.http.put<User>(`${this.apiUrl}/${id}`, user); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
```

**Explanations:**

* `@Injectable({providedIn:'root'})`: singleton service accessible globally
* `HttpClient`: Angular service for backend communication
* Each CRUD method corresponds to a backend endpoint

**Best practices:**

* Do not call the backend directly from components
* Always use a service layer

---

## 5️⃣ Components

### a) `UserListComponent`

```ts
users: User[] = [];
ngOnInit() { this.load(); }
load() { this.userService.getAll().subscribe(data => this.users = data); }
delete(id: number) { this.userService.delete(id).subscribe(() => this.load()); }
```

**Explanations:**

* `ngOnInit()`: lifecycle hook called at component initialization
* `subscribe()`: listens to the Observable returned by HttpClient
* `*ngFor` in template → loops through users
* `Router.navigate()` → navigate to the form for add/edit

### b) `UserFormComponent`

```ts
<form (ngSubmit)="save()">
  <input [(ngModel)]="user.name" name="name" required>
  <input [(ngModel)]="user.email" name="email" required type="email">
  <button type="submit">Save</button>
</form>
```

**Explanations:**

* `[(ngModel)]`: two-way data binding form ↔ property
* `ngSubmit`: form submit event
* `required` / `type="email"`: basic HTML validation

**Best practices:**

* Always check `userForm.valid` before submitting
* Handle `add` vs `edit` logic in the same component

---

## 6️⃣ Modern Routing

```ts
export const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'users/new', component: UserFormComponent },
  { path: 'users/edit/:id', component: UserFormComponent }
];
```

**Explanations:**

* `redirectTo` → redirect root route
* `:id` → route parameter retrieved via `ActivatedRoute`

---

### ✅ Angular Best Practices

* Use **standalone components** for modularity
* Singleton services for frontend business logic
* Clear separation **UI ↔ services ↔ routing**
* Observables for reactive HTTP calls

---

