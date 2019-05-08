import { ReactiveFormsModule, NG_VALIDATORS, FormsModule, FormGroup, FormControl, ValidatorFn, Validator } from '@angular/forms';
import { Directive } from '@angular/core';
@Directive({
    selector: '[jhiCvvValidator][ngModel]',
    providers: [
        {
            provide: NG_VALIDATORS,
            useExisting: CvvValidatorDirective,
            multi: true
        }
    ]
})
export class CvvValidatorDirective implements Validator {
    validator: ValidatorFn;
    constructor() {
        this.validator = this.jhiCvvValidator();
    }
    validate(c: FormControl) {
        return this.validator(c);
    }

    jhiCvvValidator(): ValidatorFn {
        return (c: FormControl) => {
            const isValid = /^[0-9]{3,4}$/.test(c.value);
            if (isValid) {
                return null;
            } else {
                return {
                    jhiCvvValidator: {
                        valid: false
                    }
                };
            }
        };
    }
}
