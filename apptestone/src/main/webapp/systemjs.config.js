/**
 * Created by pratik on 12/5/16.
 */
System.config({
    transpiler: 'typescript',
    typescriptOptions: {emitDecoratorMetadata: true},
    map: {
        'app' : 'app',
        'rxjs': 'node_modules/rxjs',

        '@angular/core'                    : 'node_modules/@angular/core',
        '@angular/common'                  : 'node_modules/@angular/common',
        '@angular/compiler'                : 'node_modules/@angular/compiler',
        '@angular/forms'                   : 'node_modules/@angular/forms',
        '@angular/platform-browser'        : 'node_modules/@angular/platform-browser',
        '@angular/platform-browser-dynamic': 'node_modules/@angular/platform-browser-dynamic',
        '@angular/router-deprecated'       : 'node_modules/@angular/router-deprecated',
        '@angular/router'                  : 'node_modules/@angular/router',
        '@angular/http'                    : 'node_modules/@angular/http',
        'ng_custom_widgets'                : 'components'
    },
    packages: {
        'app'                              : {main: 'boot.ts', defaultExtension: 'ts'},
        'components'                        : {defaultExtension : 'ts'},
        'rxjs'                             : {main: 'index.js'},
        '@angular/core'                    : {main: 'index.js'},
        '@angular/forms'                   : {main: 'index.js'},
        '@angular/http'                    : {main: 'index.js'},
        '@angular/common'                  : {main: 'index.js'},
        '@angular/compiler'                : {main: 'index.js'},
        '@angular/router'                  : {main: 'index.js'},
        '@angular/platform-browser'        : {main: 'index.js'},
        '@angular/platform-browser-dynamic': {main: 'index.js'},
        '@angular/router-deprecated'       : {main: 'index.js', defaultExtension: 'js'},
        'ng_custom_widgets'                : {main: 'all.widget.lib.ts', defaultExtension: 'ts'}
    }
});
