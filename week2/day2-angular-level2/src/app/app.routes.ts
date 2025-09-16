import { Routes } from '@angular/router';
import { UserList } from './components/user-list/user-list';
import { UserForm } from './components/user-form/user-form';

export const routes: Routes = [
    { path: '', redirectTo: 'users', pathMatch: 'full'},
    { path: 'users', component: UserList },
    { path: 'users/new', component: UserForm },
    { path: 'users/edit/:id', component: UserForm },
];
