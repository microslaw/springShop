@echo off
cd frontend
call build.cmd
cd ..

cd gateway
call build.cmd
cd ..

cd computers
call build.cmd
cd ..

cd shops
call build.cmd
cd ..
