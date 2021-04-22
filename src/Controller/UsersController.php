<?php

namespace App\Controller;

use App\Entity\Users;
use App\Form\UsersType;
use App\Repository\UsersRepository;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Filesystem\Exception\FileNotFoundException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/users')]
class UsersController extends AbstractController
{
    #[Route('/', name: 'users_index', methods: ['GET'] )]
    public function index(UsersRepository $usersRepository): Response
    {
        return $this->render('users/index.html.twig', [
            'users' => $usersRepository->findAll(),
        ]);
    }
/*
Cockamouse 🪳 🐁, date d’envoi : Aujourd’hui, à 00:20
public function ajouterUser(Request $req){

        $user= new Users();
        $form= $this->createFormBuilder($user)
            ->add('User_name',TextType::class)
            ->add('User_lastname',TextType::class)
            ->add('User_email',TextType::class)
            ->add('User_phone',TextType::class)
            ->add('User_password',PasswordType::class)
            ->add('User_photo',FileType::class)
            ->add('User_gender',ChoiceType::class,['choices'=>[  'Female'=>'female', 'Male'=>'male']])
            ->add('Ajouter',SubmitType::class)
            ->getForm();
        $form->handleRequest($req);
        if( $form->isSubmitted() && $form->isValid()){
            $user->setUserRole(0);
            $file=$user->getUserPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $user=$form->getData();
            $user->setUserPhoto($fileName);
            try{
                $file->move(
                    $this->getParameter('UserImage_directory'),$fileName
                );
            }
            catch(FileNotFoundException $e){}
            $em= $this->getDoctrine()->getManager();

            $em->persist($user);
            $em->flush();
            return $this->redirectToRoute('users_list');
        }
        return $this->render('users/ajouter.html.twig',['form' => $form->createView()]);

    }*/
    #[Route('/new', name: 'users_new', methods: ['GET', 'POST'])]
    public function new(Request $request): Response
    {
        $user = new Users();
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();

            $file = $form->get('photo')->getData();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $user=$form->getData();
            $user->setPhoto($fileName);

            $this->addFlash('info','a new user has been created');

            $entityManager->persist($user);
            $entityManager->flush();


            return $this->redirectToRoute('users_index');
        }


        return $this->render('users/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);


    }
    #[Route('/newF', name: 'users_newF', methods: ['GET', 'POST'])]
    public function newF(Request $request): Response
    {
        $user = new Users();
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();

            $file = $form->get('photo')->getData();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $user=$form->getData();
            $user->setPhoto($fileName);

            $this->addFlash('info','a new user has been created');

            $entityManager->persist($user);
            $entityManager->flush();


            return $this->redirectToRoute('users_index');
        }


        return $this->render('users/newF.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);


    }
    /**
     *
     * @Route("/searchRec",name="searchRec")
     */
    public function searchUser(UsersRepository $repository,Request $request){
        $data=$request->get('search');
        $users=$repository->findByName($data);
        return $this->render('users/index.html.twig',['users'=>$users]);
    }
    /**
     * @param UsersRepository $repository
     * @return Response
     * @Route ("/trousers",name="trousers")
     *
     */
    public function orderByMailSQL(UsersRepository $repository,Request $request){
        $data=$request->get('tri');
        $users=$repository->OrderByMail($data);
        return $this->render('users/index.html.twig',['users'=>$users]);
    }


    #[Route('/{id}', name: 'users_show', methods: ['GET'])]
    public function show(Users $user): Response
    {
        return $this->render('users/show.html.twig', [
            'user' => $user,
        ]);
    }

    #[Route('/{id}/edit', name: 'users_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Users $user): Response
    {
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('users_index');
        }

        return $this->render('users/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{id}', name: 'users_delete', methods: ['POST'])]

    public function delete(Request $request, Users $user): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('users_index');
    }

    /**
     *
     * @Route("/stats",name="stats")
     */
    public function statistique(UsersRepository $repository,Request $request){
        $data=$request->get('stats');
        $users=$repository->findAll($data);
        $name=[];
        $gender=[];
        foreach ($users as $user){
            $name[]= $user->getName();
            $gender[]=$user->getGender();

        }
        return $this->render('users/stats.html.twig',
            ['name'=> json_encode($name),
                'gender'=>json_encode($gender)]);

    }



}
