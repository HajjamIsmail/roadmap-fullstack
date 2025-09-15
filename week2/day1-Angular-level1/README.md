
---

# 🟢 Angular Documentation — Level 1: Fundamentals

## 1️⃣ Project Architecture

```
level1-angular/
├─ src/
│  ├─ app/
│  │  ├─ hello/
│  │  │  ├─ hello.component.ts
│  │  │  ├─ hello.component.html
│  │  │  └─ hello.component.scss
│  │  ├─ parent/
│  │  │  ├─ parent.component.ts
│  │  │  ├─ parent.component.html
│  │  │  └─ parent.component.scss
│  │  ├─ child/
│  │  │  ├─ child.component.ts
│  │  │  ├─ child.component.html
│  │  │  └─ child.component.scss
│  │  ├─ binding/
│  │  │  ├─ binding.component.ts
│  │  │  ├─ binding.component.html
│  │  │  └─ binding.component.scss
│  │  ├─ twoway/
│  │  │  ├─ twoway.component.ts
│  │  │  ├─ twoway.component.html
│  │  │  └─ twoway.component.scss
│  │  ├─ directives/
│  │  │  ├─ directives.component.ts
│  │  │  ├─ directives.component.html
│  │  │  └─ directives.component.scss
│  │  ├─ todo/
│  │  │  ├─ todo.component.ts
│  │  │  ├─ todo.component.html
│  │  │  └─ todo.component.scss
│  │  ├─ app.ts
│  │  ├─ app.html
│  │  └─ app.css
│  └─ main.ts
├─ index.html
└─ angular.json
```

---

## 2️⃣ Standalone Components

In Angular 16+, **each component can be standalone**:

* `standalone: true` in `@Component()`
* Import necessary modules directly in the component via `imports: []`
* No need for a traditional `AppModule`

Example:

```ts
@Component({
  selector: 'app-twoway',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './twoway.component.html',
  styleUrls: ['./twoway.component.scss']
})
```

---

## 3️⃣ List of Components and Concepts

| Concept             | Component                           | Description                                               | Angular Keywords                                                  |
| ------------------- | ----------------------------------- | --------------------------------------------------------- | ----------------------------------------------------------------- |
| Basic Component     | `HelloComponent`                    | Displays a simple message                                 | `@Component`, `{{}}` (interpolation)                              |
| Parent → Child      | `ParentComponent`, `ChildComponent` | Communication between components                          | `@Input()`, `@Output()`, `EventEmitter`, `(click)`                |
| Data Binding        | `BindingComponent`                  | Use of interpolation, property binding, and event binding | `{{}}`, `[src]`, `(click)`                                        |
| Two-way Binding     | `TwowayComponent`                   | Synchronize input and TS variable                         | `[(ngModel)]`, `FormsModule`                                      |
| Built-in Directives | `DirectivesComponent`               | Use of structural and attribute directives                | `*ngIf`, `*ngFor`, `[ngClass]`, `[ngStyle]`, `CommonModule`       |
| Mini Todo App       | `TodoComponent`                     | Task list with add/delete functionality                   | `*ngFor`, `[(ngModel)]`, `(click)`, `CommonModule`, `FormsModule` |

---

## 4️⃣ Detailed Explanation of Each Example

### 🔹 4.1 HelloComponent

**Purpose:** Demonstrate a basic component and interpolation

```ts
message = 'Hello Angular 🎉';
```

**Keywords used:**

* `@Component` → defines the component
* `{{ message }}` → interpolation to display a TS variable

---

### 🔹 4.2 Parent / Child

**Purpose:** Communication parent → child and child → parent

* `@Input()` → receives data from parent
* `@Output()` + `EventEmitter` → sends events to parent
* `(click)` → button event listener
* `$event` → receives event value from child

Example:

```ts
@Input() name!: string;
@Output() greet = new EventEmitter<string>();
sendGreet() { this.greet.emit(`Hello ${this.name}`); }
```

---

### 🔹 4.3 BindingComponent

**Purpose:** Demonstrate different types of binding

* Interpolation: `{{ title }}`
* Property binding: `[src]="imageUrl"`
* Event binding: `(click)="sayHello()"`

```html
<h3>{{ title }}</h3>
<img [src]="imageUrl">
<button (click)="sayHello()">Click me</button>
```

---

### 🔹 4.4 TwowayComponent

**Purpose:** Two-way binding with `[(ngModel)]`

* `FormsModule` is required for `ngModel`
* `[(ngModel)]="name"` → automatic synchronization between input and variable

```html
<input [(ngModel)]="name" placeholder="Type your name">
<p>Hello {{ name }}</p>
```

---

### 🔹 4.5 DirectivesComponent

**Purpose:** Use of built-in directives

* `*ngIf` → conditional display
* `*ngFor` → loop over a list
* `[ngClass]` → apply dynamic classes
* `[ngStyle]` → apply dynamic styles
* `CommonModule` is required for these directives

```html
<p *ngIf="show">This text is visible</p>
<ul>
  <li *ngFor="let item of items">{{ item }}</li>
</ul>
<p [ngClass]="{highlight: isHighlighted}">Conditional class</p>
<p [ngStyle]="{color: color}">Colored text</p>
```

---

### 🔹 4.6 TodoComponent

**Purpose:** Mini practical application

* `*ngFor` → loop over tasks
* `[(ngModel)]` → bind input for new task
* `(click)` → button for add/delete
* Required modules: `FormsModule` + `CommonModule`

```html
<input [(ngModel)]="newTask" placeholder="New task">
<button (click)="addTask()">Add</button>
<ul>
  <li *ngFor="let t of tasks; let i = index">
    {{ t }} <button (click)="removeTask(i)">❌</button>
  </li>
</ul>
```

---

## 5️⃣ Root Component `App`

* `standalone: true`
* Imports all Level 1 components
* `RouterOutlet` for routing
* Uses `signal()` for reactivity (Angular 16+)

```ts
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Hello, Parent, Binding, TwowayComponent, DirectivesComponent, TodoComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('day1-Angular-level1');
}
```

**app.html:**

```html
<h1>{{ title() }}</h1>
<app-hello></app-hello>
<app-parent></app-parent>
<app-binding></app-binding>
<app-twoway></app-twoway>
<app-directives></app-directives>
<app-todo></app-todo>
<router-outlet></router-outlet>
```

---

## 6️⃣ Angular Keywords Used in Level 1

| Keyword                      | Description                                                 |
| ---------------------------- | ----------------------------------------------------------- |
| `@Component`                 | Defines an Angular component                                |
| `standalone: true`           | Declare a standalone component                              |
| `imports: []`                | Import modules or components in a standalone component      |
| `{{ }}`                      | Interpolation to display a TS variable                      |
| `[property]`                 | Property binding (e.g., `[src]`)                            |
| `(event)`                    | Event binding (e.g., `(click)`)                             |
| `[(ngModel)]`                | Two-way binding using FormsModule                           |
| `@Input()`                   | Receive data from a parent component                        |
| `@Output()` + `EventEmitter` | Send events to a parent component                           |
| `*ngIf`                      | Structural directive for conditional display                |
| `*ngFor`                     | Structural directive for loops                              |
| `[ngClass]`                  | Attribute directive for dynamic classes                     |
| `[ngStyle]`                  | Attribute directive for dynamic styles                      |
| `FormsModule`                | Required module for `ngModel`                               |
| `CommonModule`               | Required module for `*ngIf`, `*ngFor`, `ngClass`, `ngStyle` |
| `RouterOutlet`               | Placeholder for Angular routing                             |
| `signal()`                   | Reactive state in Angular 16+                               |

---

✅ This documentation provides:

1. **Complete project structure** (architecture + folder layout)
2. **Detailed explanation of each component**
3. **Angular keywords used with their purpose**
4. Adaptation for **standalone components and Angular 16+**

---
